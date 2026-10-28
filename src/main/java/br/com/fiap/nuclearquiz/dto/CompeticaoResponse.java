package br.com.fiap.nuclearquiz.dto;

import br.com.fiap.nuclearquiz.model.Competicao;
import br.com.fiap.nuclearquiz.model.Tema;

import java.time.LocalDateTime;

public record CompeticaoResponse(
        Long id,
        String nome,
        String descricao,
        Tema tema,
        LocalDateTime dataEncerramento,
        boolean encerrada,
        Integer quantidadeQuestoes,
        Integer tempoTotalSegundos,
        String premioPrimeiroLugar,
        String premioSegundoLugar,
        String premioTerceiroLugar
) {

    public static CompeticaoResponse from(Competicao c) {
        return new CompeticaoResponse(
                c.getId(),
                c.getNome(),
                c.getDescricao(),
                c.getTema(),
                c.getDataEncerramento(),
                c.getDataEncerramento().isBefore(LocalDateTime.now()),
                c.getQuantidadeQuestoes(),
                c.getTempoTotalSegundos(),
                c.getPremioPrimeiroLugar(),
                c.getPremioSegundoLugar(),
                c.getPremioTerceiroLugar());
    }
}
