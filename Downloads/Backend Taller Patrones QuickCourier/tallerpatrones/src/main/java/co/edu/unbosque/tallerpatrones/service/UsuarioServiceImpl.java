package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.UsuarioRegistroDTO;
import co.edu.unbosque.tallerpatrones.dto.UsuarioRespuestaDTO;
import co.edu.unbosque.tallerpatrones.mapper.UsuarioMapper;
import co.edu.unbosque.tallerpatrones.model.NombreRol;
import co.edu.unbosque.tallerpatrones.model.Rol;
import co.edu.unbosque.tallerpatrones.model.Usuario;
import co.edu.unbosque.tallerpatrones.repository.RolRepository;
import co.edu.unbosque.tallerpatrones.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              RolRepository rolRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioRespuestaDTO registrar(UsuarioRegistroDTO dto) {
        if (usuarioRepository.existsByCorreoIgnoreCase(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Usuario usuario = UsuarioMapper.fromRegistroDTO(dto);

        String hash = passwordEncoder.encode(dto.getContrasena());
        usuario.setContrasenaHash(hash);

        Rol rolCliente = rolRepository.findByNombre(NombreRol.CLIENTE)
                .orElseThrow(() -> new IllegalStateException("Rol CLIENTE no configurado en la base de datos"));

        usuario.setRoles(Collections.singleton(rolCliente));

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioMapper.toRespuestaDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioRespuestaDTO buscarPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreoIgnoreCase(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + correo));

        return UsuarioMapper.toRespuestaDTO(usuario);
    }
}
