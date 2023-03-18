package mk.ukim.finki.emt_labs_spring.model.exceptions;

public class BookWithSameNameException extends RuntimeException {
    public BookWithSameNameException(String message) {
        super("A book with the name: " + message + " already exists!");
    }
}
