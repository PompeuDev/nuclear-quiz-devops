package br.com.fiap.nuclearquiz.dto;

import br.com.fiap.nuclearquiz.model.Alternativa;
import br.com.fiap.nuclearquiz.model.Tema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PerguntaRequest(

        @NotBlank(message = "O enunciado é obrigatório")
        @Size(min = 10, max = 500, message = "O enunciado deve ter entre 10 e 500 caracteres")
        String enunciado,

        @NotBlank(message = "A alternativa A é obrigatória")
        @Size(max = 200, message = "A alternativa A deve ter no máximo 200 caracteres")
        String alternativaA,

        @NotBlank(message = "A alternativa B é obrigatória")
        @Size(max = 200, message = "A alternativa B deve ter no máximo 200 caracteres")
        String alternativaB,

        @NotBlank(message = "A alternativa C é obrigatória")
        @Size(max = 200, message = "A alternativa C deve ter no máximo 200 caracteres")
        String alternativaC,

        @NotBlank(message = "A alternativa D é obrigatória")
        @Size(max = 200, message = "A alternativa D deve ter no máximo 200 caracteres")
        String alternativaD,

        @NotNull(message = "A resposta correta é obrigatória (A, B, C ou D)")
        Alternativa respostaCorreta,

        @NotBlank(message = "A explicação é obrigatória")
        @Size(max = 1000, message = "A explicação deve ter no máximo 1000 caracteres")
        String explicacao,

        @NotNull(message = "O tema é obrigatório")
        Tema tema
) {
}
