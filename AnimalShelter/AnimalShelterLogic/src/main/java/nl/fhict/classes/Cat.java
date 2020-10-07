package nl.fhict.classes;

import nl.fhict.enums.Gender;

public class Cat extends Animal {
    public String badHabits;

    public Cat(String name, Gender gender, String badHabits) {
        super(name, gender, null);
        this.badHabits = badHabits;
        this.price = calculatePrice(badHabits);
    }

    public String getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits;
    }

    public Integer calculatePrice(String badHabits){
        int charCounter = 350;
        for (int i =0; i < badHabits.length(); i++){
            if (badHabits.charAt(i) != ' ' && charCounter > 35){
                charCounter -= 20;
            }
        }
        return charCounter;
    }

    @Override
    public Integer getPrice(){
        int price = 350;
        price = price - (badHabits.length() * 20);
        return (price < 35 ) ? 35 : price;
    }

    @Override
    public String toString() {
        return String.format("%s, bad habits: %s", super.toString(), badHabits.toLowerCase());
    }
}
