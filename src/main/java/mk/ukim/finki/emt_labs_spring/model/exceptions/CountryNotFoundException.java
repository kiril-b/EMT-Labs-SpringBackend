package mk.ukim.finki.emt_labs_spring.model.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long message) {
        super("Country with id: " + " does not exist!");
    }
}
