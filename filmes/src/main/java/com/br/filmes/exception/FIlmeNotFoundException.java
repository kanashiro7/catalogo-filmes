package com.br.filmes.exception;

public class FIlmeNotFoundException extends RuntimeException {
    public FIlmeNotFoundException(Long id) {
        super("Filme com ID " + id + "não encontrado!");
    }
}
