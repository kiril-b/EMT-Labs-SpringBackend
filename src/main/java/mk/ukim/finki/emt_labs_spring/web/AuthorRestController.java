package mk.ukim.finki.emt_labs_spring.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Author;
import mk.ukim.finki.emt_labs_spring.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long countryId) {
        return authorService.save(name, surname, countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
