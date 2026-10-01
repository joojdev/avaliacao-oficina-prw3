package br.edu.ifsp.prw3.api.conserto;

import br.edu.ifsp.prw3.api.mecanico.DadosAtualizacaoMecanico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

// Somente estes campos podem ser alterados: data de saída e dados do mecânico
public record DadosAtualizacaoConserto(
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        String dataSaida,

        @Valid
        DadosAtualizacaoMecanico mecanico) {
}
