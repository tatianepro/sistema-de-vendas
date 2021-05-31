package com.github.tatianepro.rest.service.impl;

import com.github.tatianepro.VendasApplication;
import com.github.tatianepro.domain.entity.Usuario;
import com.github.tatianepro.domain.repository.UsuarioRepository;
import com.github.tatianepro.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// connfigure user to be loaded by database
@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public UserDetails autenticar(Usuario usuario) throws SenhaInvalidaException {
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean matches = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());
        if (!matches) {
            throw new SenhaInvalidaException();
        }
        return userDetails;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        String passwordEncoded = passwordEncoder.encode("123");
        System.out.println(passwordEncoded);
    }

}
