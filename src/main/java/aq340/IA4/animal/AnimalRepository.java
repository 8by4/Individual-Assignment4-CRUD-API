package aq340.IA4.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides the actual database transactions.
 */
@Repository
public interface AnimalRepository  extends JpaRepository<Animal, Integer> {

    List<Animal> getAnimalsBySpecies(String species);

    List<Animal> findByNameContaining(String string);
}
