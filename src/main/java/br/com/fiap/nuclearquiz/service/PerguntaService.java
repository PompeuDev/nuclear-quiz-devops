package br.com.fiap.nuclearquiz.service;

import br.com.fiap.nuclearquiz.dto.PerguntaRequest;
import br.com.fiap.nuclearquiz.dto.PerguntaResponse;
import br.com.fiap.nuclearquiz.exception.RecursoNaoEncontradoException;
import br.com.fiap.nuclearquiz.model.Pergunta;
import br.com.fiap.nuclearquiz.model.Tema;
import br.com.fiap.nuclearquiz.repository.PerguntaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerguntaService {

    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PerguntaResponse criar(PerguntaRequest request) {
        Pergunta pergunta = new Pergunta();
        preencher(pergunta, request);
        return PerguntaResponse.from(repository.save(pergunta));
    }

    @Transactional(readOnly = true)
    public List<PerguntaResponse> listar(Tema tema) {
        List<Pergunta> perguntas = (tema == null) ? repository.findAll() : repository.findByTema(tema);
        return perguntas.stream().map(PerguntaResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public PerguntaResponse buscarPorId(Long id) {
        return PerguntaResponse.from(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<PerguntaResponse> buscarPorEnunciado(String termo) {
        return repository.findByEnunciadoContainingIgnoreCase(termo).stream()
                .map(PerguntaResponse::from)
                .toList();
    }

    @Transactional
    public PerguntaResponse atualizar(Long id, PerguntaRequest request) {
        Pergunta pergunta = buscarEntidade(id);
        preencher(pergunta, request);
        return PerguntaResponse.from(repository.save(pergunta));
    }

    @Transactional
    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Pergunta buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pergunta", id));
    }

    private void preencher(Pergunta pergunta, PerguntaRequest request) {
        pergunta.setEnunciado(request.enunciado());
        pergunta.setAlternativaA(request.alternativaA());
        pergunta.setAlternativaB(request.alternativaB());
        pergunta.setAlternativaC(request.alternativaC());
        pergunta.setAlternativaD(request.alternativaD());
        pergunta.setRespostaCorreta(request.respostaCorreta());
        pergunta.setExplicacao(request.explicacao());
        pergunta.setTema(request.tema());
    }
}
