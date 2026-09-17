package br.edu.ifsp.prw3.api.veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosVeiculo(
        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        // ano obrigatório e no formato xxxx
        @NotBlank
        @Pattern(regexp = "\\d{4}")
        String ano,

        // cor não é obrigatória
        String cor) {
}
