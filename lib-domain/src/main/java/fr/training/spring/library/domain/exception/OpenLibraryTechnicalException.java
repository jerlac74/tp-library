package fr.training.spring.library.domain.exception;

public class OpenLibraryTechnicalException extends TechnicalException{
    public OpenLibraryTechnicalException(final Exception e) {
        super(e);
    }
}
