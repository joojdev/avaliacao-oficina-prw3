package br.edu.ifsp.prw3.api.conserto;

import br.edu.ifsp.prw3.api.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.api.veiculo.DadosVeiculo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConserto(
        // só o formato da data é validado: xx/xx/xxxx
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        String dataEntrada,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        String dataSaida,

        // @Valid faz o Bean Validation validar também os campos do record aninhado
        @NotNull
        @Valid
        DadosMecanico mecanico,

        @NotNull
        @Valid
        DadosVeiculo veiculo) {
}
