package br.com.fiap.nuclearquiz.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " com id " + id + " não foi encontrado(a)");
    }
}
