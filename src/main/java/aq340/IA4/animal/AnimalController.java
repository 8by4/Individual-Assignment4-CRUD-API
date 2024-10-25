package aq340.IA4.animal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("/all")
    public String getAllAnimals(Model model) {
        model.addAttribute("animalList", service.getAllAnimals());
        model.addAttribute("title", "All Animals");

        return "animal-list";
    }

    @GetMapping("/{animalId}")
    public String getOneAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        model.addAttribute("title", animalId);

        return "animal-details";
    }

    @GetMapping("")
    public String getAnimalsBySpecies(@RequestParam(name = "species", defaultValue = "wolf") String species, Model model) {
        model.addAttribute("animalList", service.getAnimalsBySpecies(species));
        model.addAttribute("title", "Animal Species: "+species);

        return "animal-list";
    }

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

    @PostMapping("/new")
    public String addNewAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/all";
    }

    @GetMapping("/update/{animalId}")
    public String updateAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        return "animal-update";
    }

    @PostMapping("/update")
    public String updateAnimal(Animal animal) {
        System.out.println(animal.toString());

        service.updateAnimal(animal.getAnimalId(), animal);
        return "redirect:/animals/" + animal.getAnimalId();
    }

    @GetMapping("/delete/{animalId}")
    public String deleteAnimalById(@PathVariable int animalId) {
        service.deleteAnimalById(animalId);
        return "redirect:/animals/all";
    }
}
