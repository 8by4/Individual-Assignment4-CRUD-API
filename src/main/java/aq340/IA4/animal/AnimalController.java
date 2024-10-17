package aq340.IA4.animal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java.
 * Includes all REST API endpoint mappings for the Animal object.
 */
@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    /**
     * Get a list of all Animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animal objects.
     */
    @GetMapping("/all")
    public String getAllAnimals(Model model) {
        model.addAttribute("animalList", service.getAllAnimals());
        model.addAttribute("title", "All Animals");

        return "animal-list";
    }

    /**
     * Get a specific Animal by Id.
     * http://localhost:8080/animals/2
     *
     * @param animalId the unique Id for an Animal.
     * @return One Animal object.
     */
    @GetMapping("/{animalId}")
    public String getOneAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        model.addAttribute("title", animalId);

        return "animal-details";
    }

    /**
     * Get a list of Animals based on their species.
     * http://localhost:8080/animals?species=dog
     *
     * @param species the search key.
     * @return A list of Animal objects matching the species key.
     */
    @GetMapping("")
    public String getAnimalsBySpecies(@RequestParam(name = "species", defaultValue = "wolf") String species, Model model) {
        model.addAttribute("animalList", service.getAnimalsBySpecies(species));
        model.addAttribute("title", "Animal Species: "+species);

        return "animal-list";
    }


    /**
     * Get a list of Animals based on their name with a string.
     * http://localhost:8080/animals/search?string=blue
     *
     * @param search the search key.
     * @return A list of Animal objects matching the string search key.
     */
    @GetMapping("/search")
    public String getAnimalsByString(@RequestParam String string, Model model) {
        model.addAttribute("animalList", service.getAnimalsByString(string));
        model.addAttribute("title", string);

        return "animal-details";
    }


    @GetMapping("/new")
    public String addNewAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        return "animal-create";
    }


    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new --data '{ "animalId": 4, "name": "sample",
     *                                    "description": "sample desc"}'
     *
     * @param animal the new Animal object.
     * @return the updated list of Animals.
     */
    @PostMapping("/new")
    public String addNewAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/all";
    }

    /**
     * Update an existing Animal object.
     * http://localhost:8080/animals/update/2 --data '{"animalId": 4, "name": "sampleUpdate",
     *      *                                    "scientificName": Canis Lupus, "species": "wolf", "habitat": "Most forests and mountaints",
     *      *                                    "description": "sample desc"}'
     *
     * @param animalId the unique Animal Id.
     * @param animal the new update Animal details.
     * @return the updated Animal object.
     */
    @GetMapping("/update/{animalId}")
    public String updateAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        return "animal-update";
    }


    /**
     * Perform the update.
     * @param animal
     * @return
     */
    @PostMapping("/update")
    public String updateAnimal(Animal animal) {
        System.out.println(animal.toString());

        service.updateAnimal(animal.getAnimalId(), animal);
        return "redirect:/animals/" + animal.getAnimalId();
    }


    /**
     * Delete a Animal object.
     * http://localhost:8080/animals/delete/2
     *
     * @param animalId the unique Animal Id.
     * @return the updated list of Animals.
     */
    @GetMapping("/delete/{animalId}")
    public String deleteAnimalById(@PathVariable int animalId) {
        service.deleteAnimalById(animalId);
        return "redirect:/animals/all";
    }
}
