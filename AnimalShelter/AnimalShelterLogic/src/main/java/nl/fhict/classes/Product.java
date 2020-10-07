package nl.fhict.classes;

import nl.fhict.interfaces.Sellable;


public class Product implements Sellable {

    public String name;
    public Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public Integer getPrice(){
        return this.price;
    }


    @Override
    public String toString() {
        return String.format("Name: %s, Price: %s", getName(), getPrice());
    }
}
