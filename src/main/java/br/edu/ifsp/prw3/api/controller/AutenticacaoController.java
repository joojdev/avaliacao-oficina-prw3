package br.edu.ifsp.prw3.api.controller;

import br.edu.ifsp.prw3.api.autenticacao.DadosAutenticacao;
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

    @PostMapping
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // login e senha num objeto que o Spring Security entende
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        // confere a senha (BCrypt) usando o AutenticacaoService
        manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
