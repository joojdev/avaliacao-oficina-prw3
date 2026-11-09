package br.edu.ifsp.prw3.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // usado pelo Spring Security para carregar o usuário no login
    UserDetails findByLogin(String login);
}
