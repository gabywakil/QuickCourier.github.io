package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.*;
import co.edu.unbosque.tallerpatrones.mapper.DireccionMapper;
import co.edu.unbosque.tallerpatrones.mapper.PedidoMapper;
import co.edu.unbosque.tallerpatrones.model.*;
import co.edu.unbosque.tallerpatrones.repository.ExtraEnvioRepository;
import co.edu.unbosque.tallerpatrones.repository.PedidoRepository;
import co.edu.unbosque.tallerpatrones.repository.ProductoRepository;
import co.edu.unbosque.tallerpatrones.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ExtraEnvioRepository extraEnvioRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(UsuarioRepository usuarioRepository,
                             ProductoRepository productoRepository,
                             ExtraEnvioRepository extraEnvioRepository,
                             PedidoRepository pedidoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.extraEnvioRepository = extraEnvioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoResumenDTO crearPedido(PedidoCrearDTO dto, String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreoIgnoreCase(correoUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + correoUsuario));
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado(EstadoPedido.CREADO);
        pedido.setDireccionEnvio(DireccionMapper.toEntidad(dto.getDireccionEnvio()));

        if (dto.getZonaEnvio() != null) {
            pedido.setZonaEnvio(ZonaEnvio.valueOf(dto.getZonaEnvio()));
        } else {
            pedido.setZonaEnvio(ZonaEnvio.ZONA_1);
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        int pesoTotal = 0;

        for (DetallePedidoCrearDTO detDTO : dto.getDetalles()) {
            UUID productoId = UUID.fromString(detDTO.getProductoId());
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + detDTO.getProductoId()));

            int cantidad = detDTO.getCantidad() != null ? detDTO.getCantidad() : 1;

            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal subtotalLinea = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setNombreProductoSnapshot(producto.getNombre());
            detalle.setPrecioUnitarioSnapshot(precioUnitario);
            detalle.setPesoGramosSnapshot(producto.getPesoGramos());
            detalle.setCantidad(cantidad);
            detalle.setSubtotalLinea(subtotalLinea);

            pedido.getDetalles().add(detalle);

            subtotal = subtotal.add(subtotalLinea);
            pesoTotal += producto.getPesoGramos() * cantidad;
        }

        pedido.setSubtotal(subtotal);
        pedido.setPesoTotalGramos(pesoTotal);

        BigDecimal costoExtras = BigDecimal.ZERO;

        if (dto.getExtrasEnvioIds() != null) {
            for (String extraIdStr : dto.getExtrasEnvioIds()) {
                UUID extraId = UUID.fromString(extraIdStr);
                ExtraEnvio extraEnvio = extraEnvioRepository.findById(extraId)
                        .orElseThrow(() -> new IllegalArgumentException("Extra de envío no encontrado: " + extraIdStr));

                ExtraPedido extraPedido = new ExtraPedido();
                extraPedido.setPedido(pedido);
                extraPedido.setExtraEnvio(extraEnvio);
                extraPedido.setNombreExtraSnapshot(extraEnvio.getNombre());
                extraPedido.setTipoPrecioSnapshot(extraEnvio.getTipoPrecio());
                extraPedido.setValorSnapshot(extraEnvio.getValor());

                BigDecimal costoAplicado = calcularCostoExtra(extraEnvio, subtotal, pesoTotal);
                extraPedido.setCostoAplicado(costoAplicado);

                pedido.getExtras().add(extraPedido);
                costoExtras = costoExtras.add(costoAplicado);
            }
        }

        pedido.setCostoExtras(costoExtras);

        BigDecimal costoEnvio = calcularCostoEnvioBasico(pedido);
        pedido.setCostoEnvio(costoEnvio);

        BigDecimal baseImponible = subtotal.add(costoEnvio).add(costoExtras);
        BigDecimal impuestos = baseImponible.multiply(BigDecimal.valueOf(0.19)).setScale(2, BigDecimal.ROUND_HALF_UP);
        pedido.setImpuestos(impuestos);

        BigDecimal totalGeneral = baseImponible.add(impuestos);
        pedido.setTotalGeneral(totalGeneral);
        pedido.setMoneda("COP");

        Pedido guardado = pedidoRepository.save(pedido);

        return PedidoMapper.toResumenDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResumenDTO obtenerResumenPedido(String pedidoId) {
        UUID id = UUID.fromString(pedidoId);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado: " + pedidoId));
        return PedidoMapper.toResumenDTO(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResumenDTO> listarPedidosPorUsuario(String correoUsuario) {
        List<Pedido> pedidos = pedidoRepository.findByUsuario_CorreoIgnoreCase(correoUsuario);
        return pedidos.stream()
                .map(PedidoMapper::toResumenDTO)
                .collect(Collectors.toList());
    }


    private BigDecimal calcularCostoEnvioBasico(Pedido pedido) {

        int peso = pedido.getPesoTotalGramos();
        int tramos = Math.max(0, (peso - 500 + 499) / 500);

        BigDecimal base;
        BigDecimal porTramo;

        switch (pedido.getZonaEnvio()) {
            case ZONA_2:
                base = BigDecimal.valueOf(10000);
                porTramo = BigDecimal.valueOf(1500);
                break;
            case ZONA_3:
                base = BigDecimal.valueOf(12000);
                porTramo = BigDecimal.valueOf(2000);
                break;
            case INTERNACIONAL:
                base = BigDecimal.valueOf(30000);
                porTramo = BigDecimal.valueOf(5000);
                break;
            case ZONA_1:
            default:
                base = BigDecimal.valueOf(8000);
                porTramo = BigDecimal.valueOf(1000);
        }

        return base.add(porTramo.multiply(BigDecimal.valueOf(tramos)));
    }

    private BigDecimal calcularCostoExtra(ExtraEnvio extraEnvio,
                                          BigDecimal subtotalPedido,
                                          int pesoTotalGramos) {
        switch (extraEnvio.getTipoPrecio()) {
            case MONTO_FIJO:
                return extraEnvio.getValor();
            case PORCENTAJE_SUBTOTAL:
                return subtotalPedido.multiply(extraEnvio.getValor())
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            case POR_PESO:
                BigDecimal kilos = BigDecimal.valueOf(pesoTotalGramos)
                        .divide(BigDecimal.valueOf(1000), 2, BigDecimal.ROUND_HALF_UP);
                return extraEnvio.getValor().multiply(kilos)
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            default:
                return BigDecimal.ZERO;
        }
    }
}
