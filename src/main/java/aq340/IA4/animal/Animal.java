package aq340.IA4.animal;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "animal_id")
    private int animalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "habitat", nullable = false)
    private String habitat;

    @Column(name = "description")
    private String description;

    public Animal(int animalId, String name, String scientificName, String species, String habitat, String description) {
        this.animalId = animalId;
        this.name = name;
        this.scientificName = scientificName;
        this.species = species;
        this.habitat = habitat;
        this.description = description;
    }

    public Animal(String name, String scientificName, String species, String habitat, String description) {
        this.name = name;
        this.scientificName = scientificName;
        this.species = species;
        this.habitat = habitat;
        this.description = description;
    }

    public Animal() {
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName){
        this.scientificName = scientificName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

