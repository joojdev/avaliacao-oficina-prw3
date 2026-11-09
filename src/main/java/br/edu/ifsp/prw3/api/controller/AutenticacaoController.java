package br.edu.ifsp.prw3.api.controller;

import br.edu.ifsp.prw3.api.autenticacao.DadosAutenticacao;
import br.edu.ifsp.prw3.api.usuario.Usuario;
import br.edu.ifsp.prw3.api.util.security.DadosTokenJWT;
import br.edu.ifsp.prw3.api.util.security.PW3TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PW3TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // login e senha num objeto que o Spring Security entende
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        // confere a senha (BCrypt) usando o AutenticacaoService
        var authentication = manager.authenticate(token);

        // gera o JWT do usuário autenticado e devolve dentro de um DTO
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
