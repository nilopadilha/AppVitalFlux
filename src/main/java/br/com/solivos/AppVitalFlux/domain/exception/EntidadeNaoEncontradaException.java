package br.com.solivos.AppVitalFlux.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
