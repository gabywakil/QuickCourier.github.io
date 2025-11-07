package co.edu.unbosque.tallerpatrones.controller;

import co.edu.unbosque.tallerpatrones.dto.ExtraEnvioDTO;
import co.edu.unbosque.tallerpatrones.service.ExtraEnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extras-envio")
@Tag(name = "Extras de envío", description = "Catálogo de extras que se pueden aplicar a un pedido (exprés, seguro, frágil, empaque de regalo, etc.)")
@SecurityRequirement(name = "bearerAuth")
public class ExtraEnvioController {

    private final ExtraEnvioService extraEnvioService;

    public ExtraEnvioController(ExtraEnvioService extraEnvioService) {
        this.extraEnvioService = extraEnvioService;
    }

    @Operation(
            summary = "Listar extras de envío activos",
            description = "Devuelve el listado de extras de envío que el usuario puede seleccionar al momento del checkout."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @GetMapping
    public ResponseEntity<List<ExtraEnvioDTO>> listarExtrasActivos() {
        List<ExtraEnvioDTO> extras = extraEnvioService.listarExtrasActivos();
        return ResponseEntity.ok(extras);
    }
}
