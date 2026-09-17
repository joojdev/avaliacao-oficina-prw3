package br.edu.ifsp.prw3.api.controller;

import br.edu.ifsp.prw3.api.conserto.Conserto;
import br.edu.ifsp.prw3.api.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.api.conserto.DadosCadastroConserto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroConserto dados) {
        repository.save(new Conserto(dados));
    }
}
