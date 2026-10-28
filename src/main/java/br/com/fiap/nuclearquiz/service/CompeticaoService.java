package br.com.fiap.nuclearquiz.service;

import br.com.fiap.nuclearquiz.dto.CompeticaoRequest;
import br.com.fiap.nuclearquiz.dto.CompeticaoResponse;
import br.com.fiap.nuclearquiz.exception.RecursoNaoEncontradoException;
import br.com.fiap.nuclearquiz.model.Competicao;
import br.com.fiap.nuclearquiz.repository.CompeticaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompeticaoService {

    // O tempo total cresce de forma proporcional ao número de questões
    private static final int SEGUNDOS_POR_QUESTAO = 30;

    private final CompeticaoRepository repository;

    public CompeticaoService(CompeticaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CompeticaoResponse criar(CompeticaoRequest request) {
        Competicao competicao = new Competicao();
        preencher(competicao, request);
        return CompeticaoResponse.from(repository.save(competicao));
    }

    @Transactional(readOnly = true)
    public List<CompeticaoResponse> listar() {
        return repository.findAll().stream().map(CompeticaoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public List<CompeticaoResponse> listarEmAndamento() {
        return repository.findByDataEncerramentoAfter(LocalDateTime.now()).stream()
                .map(CompeticaoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CompeticaoResponse buscarPorId(Long id) {
        return CompeticaoResponse.from(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<CompeticaoResponse> buscarPorNome(String termo) {
        return repository.findByNomeContainingIgnoreCase(termo).stream()
                .map(CompeticaoResponse::from)
                .toList();
    }

    @Transactional
    public CompeticaoResponse atualizar(Long id, CompeticaoRequest request) {
        Competicao competicao = buscarEntidade(id);
        preencher(competicao, request);
        return CompeticaoResponse.from(repository.save(competicao));
    }

    @Transactional
    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Competicao buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Competição", id));
    }

    private void preencher(Competicao competicao, CompeticaoRequest request) {
        competicao.setNome(request.nome());
        competicao.setDescricao(request.descricao());
        competicao.setTema(request.tema());
        competicao.setDataEncerramento(request.dataEncerramento());
        competicao.setQuantidadeQuestoes(request.quantidadeQuestoes());
        competicao.setTempoTotalSegundos(request.quantidadeQuestoes() * SEGUNDOS_POR_QUESTAO);
        competicao.setPremioPrimeiroLugar(request.premioPrimeiroLugar());
        competicao.setPremioSegundoLugar(request.premioSegundoLugar());
        competicao.setPremioTerceiroLugar(request.premioTerceiroLugar());
    }
}
