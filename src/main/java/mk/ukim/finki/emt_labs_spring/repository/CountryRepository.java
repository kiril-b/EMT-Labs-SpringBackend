package mk.ukim.finki.emt_labs_spring.repository;

import mk.ukim.finki.emt_labs_spring.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
