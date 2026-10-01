package br.edu.ifsp.prw3.api.controller;

import br.edu.ifsp.prw3.api.conserto.Conserto;
import br.edu.ifsp.prw3.api.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.api.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3.api.conserto.DadosDetalhamentoConserto;
import br.edu.ifsp.prw3.api.conserto.DadosListagemConserto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public Page<DadosDetalhamentoConserto> listar(Pageable paginacao) {
        // todos os dados, paginados (ex.: ?page=0&size=10&sort=dataEntrada)
        return repository.findAll(paginacao).map(DadosDetalhamentoConserto::new);
    }

    @GetMapping("algunsdados")
    public List<DadosListagemConserto> listarAlgunsDados() {
        // dados parciais, sem paginação, apenas dos consertos ativos
        return repository.findAllByAtivoTrue().stream()
                .map(DadosListagemConserto::new)
                .toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConserto> detalhar(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoConserto(consertoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
