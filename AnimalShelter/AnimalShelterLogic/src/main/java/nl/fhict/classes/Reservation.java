package nl.fhict.classes;

import nl.fhict.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    public List<Animal> animals = new ArrayList<Animal>();

    public void newCat(String name, Gender gender, String badHabits) {
        this.animals.add(new Cat(name, gender, badHabits));
    }

    public void newDog(String name, Gender gender) {
        this.animals.add(new Dog(name, gender));
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
