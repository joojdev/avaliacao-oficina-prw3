package br.edu.ifsp.prw3.api.conserto;

import br.edu.ifsp.prw3.api.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.api.veiculo.DadosVeiculo;

// Todos os dados do conserto (usado nas respostas para não devolver a entidade JPA)
public record DadosDetalhamentoConserto(
        Long id,
        String dataEntrada,
        String dataSaida,
        DadosMecanico mecanico,
        DadosVeiculo veiculo,
        Boolean ativo) {

    public DadosDetalhamentoConserto(Conserto conserto) {
        this(conserto.getId(),
                conserto.getDataEntrada(),
                conserto.getDataSaida(),
                new DadosMecanico(conserto.getMecanico()),
                new DadosVeiculo(conserto.getVeiculo()),
                conserto.getAtivo());
    }
}
