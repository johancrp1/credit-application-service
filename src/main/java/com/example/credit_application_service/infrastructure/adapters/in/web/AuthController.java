package com.example.credit_application_service.infrastructure.adapters.in.web;

import com.example.credit_application_service.domain.model.UsuarioModel;
import com.example.credit_application_service.domain.ports.out.UsuarioRepositoryPort;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.AuthRequest;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.AuthResponse;
import com.example.credit_application_service.infrastructure.config.security.JWTProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public AuthController(UsuarioRepositoryPort usuarioRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        UsuarioModel usuario = new UsuarioModel(
                UUID.randomUUID(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRoles()
        );
        usuarioRepository.save(usuario);
        String token = jwtProvider.generateToken(usuario.getUsername(), usuario.getRoles());
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        UsuarioModel usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Password incorrecta");
        }

        String token = jwtProvider.generateToken(usuario.getUsername(), usuario.getRoles());
        return new AuthResponse(token);
    }
}
