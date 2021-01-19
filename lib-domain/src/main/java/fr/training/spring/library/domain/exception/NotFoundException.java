package fr.training.spring.library.domain.exception;

public class NotFoundException extends RuntimeException {
    private String errorCodes;

    public NotFoundException(String s, String errorCodes) {
        super(s);
        this.errorCodes =errorCodes;
    }

    public String getErrorCodes() {
        return errorCodes;
    }
}
