package co.edu.unbosque.tallerpatrones.mapper;

import co.edu.unbosque.tallerpatrones.dto.*;
import co.edu.unbosque.tallerpatrones.model.DetallePedido;
import co.edu.unbosque.tallerpatrones.model.ExtraPedido;
import co.edu.unbosque.tallerpatrones.model.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    private PedidoMapper() {}

    public static PedidoResumenDTO toResumenDTO(Pedido entidad) {
        if (entidad == null) return null;

        PedidoResumenDTO dto = new PedidoResumenDTO();
        dto.setId(entidad.getId().toString());
        dto.setEstado(entidad.getEstado().name());

        if (entidad.getUsuario() != null) {
            dto.setCorreoUsuario(entidad.getUsuario().getCorreo());
        }

        dto.setDireccionEnvio(DireccionMapper.toDTO(entidad.getDireccionEnvio()));

        if (entidad.getZonaEnvio() != null) {
            dto.setZonaEnvio(entidad.getZonaEnvio().name());
        }

        dto.setPesoTotalGramos(entidad.getPesoTotalGramos());

        dto.setSubtotal(entidad.getSubtotal());
        dto.setCostoEnvio(entidad.getCostoEnvio());
        dto.setCostoExtras(entidad.getCostoExtras());
        dto.setImpuestos(entidad.getImpuestos());
        dto.setTotalGeneral(entidad.getTotalGeneral());
        dto.setMoneda(entidad.getMoneda());

        List<DetallePedidoDTO> detallesDTO = entidad.getDetalles()
                .stream()
                .map(PedidoMapper::toDetalleDTO)
                .collect(Collectors.toList());
        dto.setDetalles(detallesDTO);

        // Extras
        List<ExtraPedidoDTO> extrasDTO = entidad.getExtras()
                .stream()
                .map(PedidoMapper::toExtraDTO)
                .collect(Collectors.toList());
        dto.setExtras(extrasDTO);

        return dto;
    }

    private static DetallePedidoDTO toDetalleDTO(DetallePedido d) {
        if (d == null) return null;

        DetallePedidoDTO dto = new DetallePedidoDTO();

        if (d.getProducto() != null && d.getProducto().getId() != null) {
            dto.setProductoId(d.getProducto().getId().toString());
        }

        dto.setNombreProducto(d.getNombreProductoSnapshot());
        dto.setPrecioUnitario(d.getPrecioUnitarioSnapshot());
        dto.setCantidad(d.getCantidad());
        dto.setSubtotalLinea(d.getSubtotalLinea());
        return dto;
    }

    private static ExtraPedidoDTO toExtraDTO(ExtraPedido e) {
        if (e == null) return null;

        ExtraPedidoDTO dto = new ExtraPedidoDTO();

        if (e.getExtraEnvio() != null && e.getExtraEnvio().getId() != null) {
            dto.setExtraEnvioId(e.getExtraEnvio().getId().toString());
        }

        dto.setNombreExtra(e.getNombreExtraSnapshot());
        dto.setTipoPrecio(e.getTipoPrecioSnapshot().name());
        dto.setValorConfigurado(e.getValorSnapshot());
        dto.setCostoAplicado(e.getCostoAplicado());
        return dto;
    }
}
