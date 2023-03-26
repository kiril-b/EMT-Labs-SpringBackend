package mk.ukim.finki.emt_labs_spring.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Book;
import mk.ukim.finki.emt_labs_spring.model.enumerations.Category;
import mk.ukim.finki.emt_labs_spring.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookRestController {

    private final BookService bookService;

    @GetMapping({"/", "/books"})
    public List<Book> findAllWithPagination(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {

        return bookService.findAllWithPagination(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/books/num-books")
    public ResponseEntity<Long> getNumberOfBooks() {
        return this.bookService.numberOfBooks()
                .map(num -> ResponseEntity.ok().body(num))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/books/save")
    public ResponseEntity<Book> save(@RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam Integer availableCopies,
                                     @RequestParam Long authorId) {
        return this.bookService.save(name, category, availableCopies, authorId)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestParam String name,
                                     @RequestParam Category category,
                                     @RequestParam Integer availableCopies,
                                     @RequestParam Long authorId) {
        return this.bookService.edit(id, name, category, availableCopies, authorId)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/books/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/books/available-copies/{id}")
    public ResponseEntity<Book> decrementAvailableCopies(@PathVariable Long id) {
        return this.bookService.decrementAvailableCopies(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
