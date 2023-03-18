package mk.ukim.finki.emt_labs_spring.model.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long message) {
        super("Author with id: " + message + " not found!");
    }
}
