package br.edu.ifsp.prw3.api.conserto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    // o Spring Data monta a consulta pelo nome do método: where ativo = true
    List<Conserto> findAllByAtivoTrue();
}
