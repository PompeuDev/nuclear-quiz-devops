package br.com.fiap.nuclearquiz.dto;

import br.com.fiap.nuclearquiz.model.Alternativa;
import br.com.fiap.nuclearquiz.model.Pergunta;
import br.com.fiap.nuclearquiz.model.Tema;

public record PerguntaResponse(
        Long id,
        String enunciado,
        String alternativaA,
        String alternativaB,
        String alternativaC,
        String alternativaD,
        Alternativa respostaCorreta,
        String explicacao,
        Tema tema
) {

    public static PerguntaResponse from(Pergunta p) {
        return new PerguntaResponse(
                p.getId(),
                p.getEnunciado(),
                p.getAlternativaA(),
                p.getAlternativaB(),
                p.getAlternativaC(),
                p.getAlternativaD(),
                p.getRespostaCorreta(),
                p.getExplicacao(),
                p.getTema());
    }
}
