package br.edu.ifsp.prw3.api.util.security;

import br.edu.ifsp.prw3.api.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PW3TokenService {

    private static final String ISSUER = "API OFICINA PW3";

    public String gerarToken(Usuario usuario) {
        try {
            // HMAC com SHA-256: só quem tem a chave secreta consegue gerar/validar o token
            var algoritmo = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    // token vale por 2 horas a partir de agora
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
