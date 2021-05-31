package com.github.tatianepro.rest.controller;

import com.github.tatianepro.domain.entity.Usuario;
import com.github.tatianepro.exception.SenhaInvalidaException;
import com.github.tatianepro.rest.dto.CredenciaisDto;
import com.github.tatianepro.rest.dto.TokenDto;
import com.github.tatianepro.rest.service.impl.UsuarioServiceImpl;
import com.github.tatianepro.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UsuarioController(UsuarioServiceImpl usuarioService,
                             PasswordEncoder passwordEncoder,
                             JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDto authenticate(@RequestBody @Valid CredenciaisDto credenciaisDto) {
        try {
            Usuario usuario = new Usuario(credenciaisDto.getLogin(), credenciaisDto.getSenha());
            UserDetails userDetails = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getLogin(), token);
        } catch (SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
