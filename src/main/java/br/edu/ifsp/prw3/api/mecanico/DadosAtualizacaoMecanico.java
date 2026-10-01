package br.edu.ifsp.prw3.api.mecanico;

// Na atualização todos os campos são opcionais: só muda o que vier no JSON
public record DadosAtualizacaoMecanico(String nome, Integer anosExperiencia) {
}
