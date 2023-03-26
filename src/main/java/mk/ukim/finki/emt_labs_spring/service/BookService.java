package mk.ukim.finki.emt_labs_spring.service;

import mk.ukim.finki.emt_labs_spring.model.Book;
import mk.ukim.finki.emt_labs_spring.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Optional<Long> numberOfBooks();
    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Integer availableCopies, Long authorId);

    Optional<Book> edit(Long bookId, String name, Category category, Integer availableCopies, Long authorId);

    Optional<Book> decrementAvailableCopies(Long bookId);

    void deleteById(Long bookId);

}
