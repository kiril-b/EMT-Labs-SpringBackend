package mk.ukim.finki.emt_labs_spring.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt_labs_spring.model.Author;
import mk.ukim.finki.emt_labs_spring.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long countryId) {
        return authorService.save(name, surname, countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
