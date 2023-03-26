package mk.ukim.finki.emt_labs_spring.web;


import mk.ukim.finki.emt_labs_spring.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {

    @GetMapping
    public List<Category> listCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
