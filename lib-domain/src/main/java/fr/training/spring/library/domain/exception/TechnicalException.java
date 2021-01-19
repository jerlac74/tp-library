package fr.training.spring.library.domain.exception;

public class TechnicalException extends RuntimeException{
    public TechnicalException(final Exception e) {
        super(e);
    }
}
