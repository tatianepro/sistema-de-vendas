package com.github.tatianepro.security.jwt;

import com.github.tatianepro.VendasApplication;
import com.github.tatianepro.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expString;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        Long expLong = Long.parseLong(expString);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expLong);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        // claims can add more information to the token payload
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("emailDoUsuario", "usuario@gmail.com");
//        claims.put("roles", "admin");

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
//                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenIsValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao_Date = claims.getExpiration();
            LocalDateTime dataExpiracao_LocalDateTime = dataExpiracao_Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(dataExpiracao_LocalDateTime);
        } catch (Exception ex) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

    // testing the generated token
    public static void main(String[] args) {
        // test encrypting
        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        JwtService service = context.getBean(JwtService.class);
        Usuario usuario = new Usuario();
        usuario.setLogin("cicrano");
        String token = service.gerarToken(usuario);
        System.out.println(token);

        // test decrypting
        boolean tokenIsValido = service.tokenIsValido(token);
        System.out.println("Token is valid? " + tokenIsValido);

        System.out.println("subject: " + service.obterLoginUsuario(token));
    }

}
