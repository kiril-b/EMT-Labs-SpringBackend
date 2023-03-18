package mk.ukim.finki.emt_labs_spring.service;

import mk.ukim.finki.emt_labs_spring.model.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
}
