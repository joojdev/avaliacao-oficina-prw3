package br.edu.ifsp.prw3.api.controller;

import br.edu.ifsp.prw3.api.conserto.Conserto;
import br.edu.ifsp.prw3.api.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.api.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3.api.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3.api.conserto.DadosDetalhamentoConserto;
import br.edu.ifsp.prw3.api.conserto.DadosListagemConserto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> cadastrar(@RequestBody @Valid DadosCadastroConserto dados,
                                                               UriComponentsBuilder uriBuilder) {
        Conserto conserto = new Conserto(dados);
        repository.save(conserto);

        // 201 Created com o endereço do novo recurso no cabeçalho Location
        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConserto(conserto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConserto>> listar(Pageable paginacao) {
        // todos os dados, paginados (ex.: ?page=0&size=10&sort=dataEntrada)
        Page<DadosDetalhamentoConserto> page = repository.findAll(paginacao).map(DadosDetalhamentoConserto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("algunsdados")
    public ResponseEntity<List<DadosListagemConserto>> listarAlgunsDados() {
        // dados parciais, sem paginação, apenas dos consertos ativos
        List<DadosListagemConserto> lista = repository.findAllByAtivoTrue().stream()
                .map(DadosListagemConserto::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConserto> detalhar(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoConserto(consertoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> atualizar(@PathVariable Long id,
                                                               @RequestBody @Valid DadosAtualizacaoConserto dados) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            Conserto conserto = consertoOptional.get();
            // não precisa de save: dentro da transação a JPA sincroniza as alterações sozinha
            conserto.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            // exclusão lógica: apenas marca como inativo
            consertoOptional.get().excluir();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
