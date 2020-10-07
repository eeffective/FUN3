package nl.fhict.classes;

import nl.fhict.enums.Gender;
import nl.fhict.interfaces.Sellable;

import java.time.LocalDate;

public abstract class Animal implements Sellable {
    public String name;
    public Integer price;
    public Gender gender;
    public Reservor reservedBy;

    public Animal(String name, Gender gender, Integer price) {
        this.name = name;
        this.gender = gender;
        this.price = price;
    }


    public boolean Reserve(String reservedBy){
        if (this.reservedBy == null){
            this.reservedBy = new Reservor(reservedBy, LocalDate.now());
            return true;
        } return false;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Reservor getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Reservor reservedBy) {
        this.reservedBy = reservedBy;
    }

    @Override
    public String toString() {
        String reserved = "Not reserved";
        if (reservedBy != null) {
            reserved = String.format("Reserved by %s", reservedBy.getName());
        }
        return String.format("%s, %s, %s, %s", name, gender, price, reserved);
    }

}
