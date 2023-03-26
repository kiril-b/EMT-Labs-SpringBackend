package mk.ukim.finki.emt_labs_spring.service;

import mk.ukim.finki.emt_labs_spring.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {


    List<Author> findAll();
    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> edit(Long authorId, String name, String surname, Long countryId);

}
