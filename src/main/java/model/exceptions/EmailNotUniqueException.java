package model.exceptions;

public class EmailNotUniqueException extends RuntimeException{
	public EmailNotUniqueException(String message) {
        super(message);
    }
}
