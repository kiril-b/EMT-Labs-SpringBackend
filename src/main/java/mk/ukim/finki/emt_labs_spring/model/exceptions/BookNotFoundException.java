package mk.ukim.finki.emt_labs_spring.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long message) {
        super("A book with id: " + message + " does not exist!");
    }
}
