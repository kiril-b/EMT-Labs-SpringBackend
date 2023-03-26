package mk.ukim.finki.emt_labs_spring.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Author;
import mk.ukim.finki.emt_labs_spring.model.Book;
import mk.ukim.finki.emt_labs_spring.model.enumerations.Category;
import mk.ukim.finki.emt_labs_spring.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt_labs_spring.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt_labs_spring.model.exceptions.BookWithSameNameException;
import mk.ukim.finki.emt_labs_spring.repository.AuthorRepository;
import mk.ukim.finki.emt_labs_spring.repository.BookRepository;
import mk.ukim.finki.emt_labs_spring.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Optional<Long> numberOfBooks() {
        return Optional.of(this.bookRepository.count());
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Integer availableCopies, Long authorId) {
        if (this.bookRepository.findByName(name).isPresent()) {
            throw new BookWithSameNameException(name);
        }

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        Book newBook = new Book(name, category, availableCopies, author);

        return Optional.of(bookRepository.save(newBook));
    }

    @Override
    public Optional<Book> edit(Long bookId, String name, Category category, Integer availableCopies, Long authorId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> decrementAvailableCopies(Long bookId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.decrementAvailableCopies();
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
