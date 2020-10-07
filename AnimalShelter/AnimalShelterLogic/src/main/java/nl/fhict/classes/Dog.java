package nl.fhict.classes;

import nl.fhict.enums.Gender;

import java.time.Duration;
import java.time.LocalDate;

public class Dog extends Animal {
    public LocalDate lastWalk;
    public static Integer priceDog = 500;
    public boolean walkNeeded;

    public Dog(String name, Gender gender) {
        super(name, gender, priceDog);
        this.lastWalk = LocalDate.now();
        if (priceDog > 50) {
            priceDog = priceDog - 50;
        }
    }

    public boolean isWalkNeeded() {
        return Math.abs(Duration.between(LocalDate.now().atStartOfDay(), lastWalk.atStartOfDay()).toDays()) > 0;
    }

    public void setWalkNeeded(boolean walkNeeded) {
        this.walkNeeded = walkNeeded;
    }

    public LocalDate getLastWalk() {
        return lastWalk;
    }

    public void setLastWalk(LocalDate lastWalk) {
        this.lastWalk = lastWalk;
    }

    @Override
    public String toString() {
        return String.format("%s, last walk: %s", super.toString(), lastWalk);
    }

}

