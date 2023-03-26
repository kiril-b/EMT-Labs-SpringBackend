package mk.ukim.finki.emt_labs_spring.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Author;
import mk.ukim.finki.emt_labs_spring.model.Country;
import mk.ukim.finki.emt_labs_spring.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt_labs_spring.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt_labs_spring.repository.AuthorRepository;
import mk.ukim.finki.emt_labs_spring.repository.CountryRepository;
import mk.ukim.finki.emt_labs_spring.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        Author newAuthor = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(newAuthor));
    }

    @Override
    public Optional<Author> edit(Long authorId, String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }
}
