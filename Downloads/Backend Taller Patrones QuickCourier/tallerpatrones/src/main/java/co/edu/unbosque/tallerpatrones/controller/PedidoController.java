package co.edu.unbosque.tallerpatrones.controller;

import co.edu.unbosque.tallerpatrones.dto.PedidoCrearDTO;
import co.edu.unbosque.tallerpatrones.dto.PedidoResumenDTO;
import co.edu.unbosque.tallerpatrones.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con la creación y consulta de pedidos")
@SecurityRequirement(name = "bearerAuth")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(
            summary = "Crear pedido",
            description = "Crea un nuevo pedido para el usuario autenticado, con productos y extras de envío."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos (producto o extra no encontrado, etc.)"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @PostMapping
    public ResponseEntity<PedidoResumenDTO> crearPedido(@RequestBody PedidoCrearDTO dto,
                                                        Authentication authentication) {
        String correoUsuario = authentication.getName();
        PedidoResumenDTO resumen = pedidoService.crearPedido(dto, correoUsuario);
        return ResponseEntity.ok(resumen);
    }

    @Operation(
            summary = "Obtener pedido por ID",
            description = "Devuelve el resumen de un pedido específico."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "400", description = "ID inválido"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResumenDTO> obtenerPedido(@PathVariable String id) {
        PedidoResumenDTO resumen = pedidoService.obtenerResumenPedido(id);
        return ResponseEntity.ok(resumen);
    }

    @Operation(
            summary = "Listar pedidos del usuario autenticado",
            description = "Lista todos los pedidos asociados al usuario del token JWT."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @GetMapping("/mis")
    public ResponseEntity<List<PedidoResumenDTO>> listarMisPedidos(Authentication authentication) {
        String correoUsuario = authentication.getName();
        List<PedidoResumenDTO> pedidos = pedidoService.listarPedidosPorUsuario(correoUsuario);
        return ResponseEntity.ok(pedidos);
    }
}
