package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.PedidoCrearDTO;
import co.edu.unbosque.tallerpatrones.dto.PedidoResumenDTO;

import java.util.List;

public interface PedidoService {

    PedidoResumenDTO crearPedido(PedidoCrearDTO dto, String correoUsuario);

    PedidoResumenDTO obtenerResumenPedido(String pedidoId);

    List<PedidoResumenDTO> listarPedidosPorUsuario(String correoUsuario);
}

