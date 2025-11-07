package co.edu.unbosque.tallerpatrones.controller;

import co.edu.unbosque.tallerpatrones.dto.UsuarioRegistroDTO;
import co.edu.unbosque.tallerpatrones.dto.UsuarioRespuestaDTO;
import co.edu.unbosque.tallerpatrones.dto.JwtLoginResponse;
import co.edu.unbosque.tallerpatrones.security.JwtTokenService;
import co.edu.unbosque.tallerpatrones.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para registro y login de usuarios")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenService jwtTokenService,
                          UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Registro de usuario",
            description = "Crea un nuevo usuario con rol CLIENTE por defecto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o correo ya registrado")
    })
    @PostMapping("/registro")
    public ResponseEntity<UsuarioRespuestaDTO> registrar(@RequestBody UsuarioRegistroDTO dto) {
        UsuarioRespuestaDTO creado = usuarioService.registrar(dto);
        return ResponseEntity.ok(creado);
    }

    @Operation(
            summary = "Login",
            description = "Autentica al usuario usando correo y contraseña, y devuelve un token JWT para acceder al resto de la API."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login correcto, se devuelve el token"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRegistroDTO loginDto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getCorreo(),
                            loginDto.getContrasena()
                    )
            );

            String token = jwtTokenService.generarToken(loginDto.getCorreo());
            UsuarioRespuestaDTO usuario = usuarioService.buscarPorCorreo(loginDto.getCorreo());

            return ResponseEntity.ok(new JwtLoginResponse(token, usuario));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}

