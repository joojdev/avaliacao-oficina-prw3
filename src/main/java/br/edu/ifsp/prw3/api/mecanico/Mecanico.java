package br.edu.ifsp.prw3.api.mecanico;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Embeddable: os atributos do mecânico viram colunas da própria tabela consertos
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    private String nome;
    private Integer anosExperiencia;

    public Mecanico(DadosMecanico dados) {
        this.nome = dados.nome();
        this.anosExperiencia = dados.anosExperiencia();
    }

    public void atualizarInformacoes(DadosAtualizacaoMecanico dados) {
        // o nome é obrigatório: só troca se vier preenchido
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.anosExperiencia() != null) {
            this.anosExperiencia = dados.anosExperiencia();
        }
    }
}
