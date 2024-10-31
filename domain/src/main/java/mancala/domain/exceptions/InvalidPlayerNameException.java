package mancala.domain.exceptions;

public class InvalidPlayerNameException extends RuntimeException {
    public InvalidPlayerNameException(String message) {
        super(message);
    }
}
