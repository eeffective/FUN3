package nl.fhict.classes;

import nl.fhict.interfaces.Sellable;

import java.util.ArrayList;

public class Shop {

    private ArrayList<Sellable> sellables;
    private Integer cashRegister;

    public Shop(ArrayList<Sellable> sellables, Integer cashRegister) {
        this.sellables = sellables;
        this.cashRegister = cashRegister;
    }

    public ArrayList<Sellable> getSellables() {
        return sellables;
    }

    public void setSellables(ArrayList<Sellable> sellables) {
        this.sellables = sellables;
    }

    public Integer getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(Integer cashRegister) {
        this.cashRegister = cashRegister;
    }

    public void sell(Sellable sellable, ArrayList<Sellable> sellables){
        sellables.remove(sellable);
        this.cashRegister += sellable.getPrice();
    }

    public void sellProduct(Product product, ArrayList<Sellable> sellableList){
        sellableList.remove(product);
    }
}

