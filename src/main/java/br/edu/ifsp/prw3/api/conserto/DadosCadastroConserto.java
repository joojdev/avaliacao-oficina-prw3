package br.edu.ifsp.prw3.api.conserto;

import br.edu.ifsp.prw3.api.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.api.veiculo.DadosVeiculo;

public record DadosCadastroConserto(
        String dataEntrada,
        String dataSaida,
        DadosMecanico mecanico,
        DadosVeiculo veiculo) {
}
