package co.edu.unbosque.tallerpatrones.controller;

import co.edu.unbosque.tallerpatrones.dto.UsuarioRespuestaDTO;
import co.edu.unbosque.tallerpatrones.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios autenticados")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Obtener perfil del usuario autenticado",
            description = "Devuelve la información del usuario asociado al token JWT enviado en la petición."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    @GetMapping("/yo")
    public ResponseEntity<UsuarioRespuestaDTO> obtenerMiPerfil(Authentication authentication) {
        String correo = authentication.getName();
        UsuarioRespuestaDTO usuario = usuarioService.buscarPorCorreo(correo);
        return ResponseEntity.ok(usuario);
    }
}
