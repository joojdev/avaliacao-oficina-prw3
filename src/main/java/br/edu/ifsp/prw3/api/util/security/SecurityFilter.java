package br.edu.ifsp.prw3.api.util.security;

import br.edu.ifsp.prw3.api.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// executado uma única vez por requisição, antes dos filtros do Spring Security
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private PW3TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // o token vem no cabeçalho Authorization ("Bearer <token>")
        var authorizationHeader = request.getHeader("Authorization");

        // /login não envia token, então só autentica quando o cabeçalho existe
        if (authorizationHeader != null) {
            var tokenJWT = authorizationHeader.replace("Bearer ", "");
            try {
                // valida o token e recupera o login do usuário
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = repository.findByLogin(subject);

                if (usuario != null) {
                    // API stateless: o usuário é autenticado de novo em cada requisição
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                            usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException e) {
                // token inválido ou expirado: segue sem autenticar e o Spring devolve 403
                SecurityContextHolder.clearContext();
            }
        }

        // segue para o próximo filtro
        filterChain.doFilter(request, response);
    }
}
