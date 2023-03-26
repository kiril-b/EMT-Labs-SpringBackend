package mk.ukim.finki.emt_labs_spring.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Author;
import mk.ukim.finki.emt_labs_spring.model.Country;
import mk.ukim.finki.emt_labs_spring.repository.AuthorRepository;
import mk.ukim.finki.emt_labs_spring.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;

    @PostConstruct
    public void init() {
        Country rus = new Country("Russia", "Europe/Asia");
        Country fr = new Country("France", "Europe");

        Author albert = new Author("Albert", "Camus", fr);

        this.countryRepository.save(rus);
        this.countryRepository.save(fr);
        this.authorRepository.save(albert);
    }
}
