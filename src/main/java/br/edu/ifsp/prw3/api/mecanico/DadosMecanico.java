package br.edu.ifsp.prw3.api.mecanico;

import jakarta.validation.constraints.NotBlank;

public record DadosMecanico(
        // nome do mecânico é obrigatório
        @NotBlank
        String nome,

        Integer anosExperiencia) {
}
