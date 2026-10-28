package br.com.fiap.nuclearquiz.dto;

import br.com.fiap.nuclearquiz.model.Tema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CompeticaoRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "O tema é obrigatório")
        Tema tema,

        @NotNull(message = "A data de encerramento é obrigatória")
        @Future(message = "A data de encerramento deve estar no futuro")
        LocalDateTime dataEncerramento,

        @NotNull(message = "A quantidade de questões é obrigatória")
        @Min(value = 4, message = "A competição deve ter no mínimo 4 questões")
        @Max(value = 10, message = "A competição deve ter no máximo 10 questões")
        Integer quantidadeQuestoes,

        @NotBlank(message = "O prêmio do 1º lugar é obrigatório")
        @Size(max = 100, message = "O prêmio do 1º lugar deve ter no máximo 100 caracteres")
        String premioPrimeiroLugar,

        @NotBlank(message = "O prêmio do 2º lugar é obrigatório")
        @Size(max = 100, message = "O prêmio do 2º lugar deve ter no máximo 100 caracteres")
        String premioSegundoLugar,

        @NotBlank(message = "O prêmio do 3º lugar é obrigatório")
        @Size(max = 100, message = "O prêmio do 3º lugar deve ter no máximo 100 caracteres")
        String premioTerceiroLugar
) {
}
