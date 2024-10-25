package aq340.IA4.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(int animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

    public List<Animal> getAnimalsBySpecies(String species) {
        return animalRepository.getAnimalsBySpecies(species);
    }

    public List<Animal> getAnimalsByString(String string) {
        return animalRepository.findByNameContaining(string);
    }

    /**
     * Add a new Animal to the database.
     *
     * @param animal the new Animal to add.
     */
    public void addNewAnimal(Animal animal) {animalRepository.save(animal);
    }

    /**
     * Update an existing Animal.
     *
     * @param animalId the unique Animal Id.
     * @param animal the new Animal details.
     */
    public void updateAnimal(int animalId, Animal animal) {
        Animal existing = getAnimalById(animalId);
        existing.setName(animal.getName());
        existing.setScientificName(animal.getScientificName());
        existing.setSpecies(animal.getSpecies());
        existing.setHabitat(animal.getHabitat());
        existing.setDescription(animal.getDescription());

        //Technically the 6 lines above are not necessary because the save method merges by default.
        animalRepository.save(existing);
    }

    /**
     * Delete a unique Animal.
     *
     * @param animalId the unique Animal Id.
     */
    public void deleteAnimalById(int animalId) {
        animalRepository.deleteById(animalId);
    }
}