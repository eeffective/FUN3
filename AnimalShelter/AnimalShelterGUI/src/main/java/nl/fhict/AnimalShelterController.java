package nl.fhict;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import nl.fhict.classes.*;
import nl.fhict.enums.Gender;
import nl.fhict.interfaces.Sellable;

import java.time.LocalDate;
import java.util.ArrayList;


public class AnimalShelterController {

    @FXML
    private ChoiceBox choiceSpecies;
    @FXML
    private TextField textNameAnimal;
    @FXML
    private TextField textNameProduct;
    @FXML
    private TextField textPriceProduct;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private ListView listAnimals;
    @FXML
    private ListView listProducts;
    @FXML
    private TextField textNameReservor;
    @FXML
    private TextField textBadHabits;
    @FXML
    private Button btnAddAnimal;
    @FXML
    private Button btnSellAnimal;
    @FXML
    private Button btnSellProduct;
    @FXML
    private Button btnReserveAnimal;
    @FXML
    private Label labelCash;
    private Alert alert;
    private Shop shop;
    private ArrayList<Sellable> sellables;

    @FXML
    public void initialize() {
        sellables = new ArrayList<>();
        shop = new Shop(sellables, 0);
        choiceSpecies.setItems(FXCollections.observableArrayList(Dog.class.getSimpleName(), Cat.class.getSimpleName()));
    }

    @FXML
    private void toggleSpecies() {
        if (choiceSpecies.getValue() == Dog.class.getSimpleName()) {
            textBadHabits.setDisable(true);
        } else if (choiceSpecies.getValue() == Cat.class.getSimpleName()) {
            textBadHabits.setDisable(false);
        }
    }

    @FXML
    private void btnAddAnimal(MouseEvent event) {
        if (textNameAnimal.getText().trim().isEmpty()) {
            alert = new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Animal Shelter | Error");
            alert.setContentText("Please fill in the name of the animal");
            alert.showAndWait();
        } else if (radioMale.isSelected()) {
            addAnimal((String) choiceSpecies.getValue(), Gender.Male, textBadHabits.getText());
        } else if (radioFemale.isSelected()) {
            addAnimal((String) choiceSpecies.getValue(), Gender.Female, textBadHabits.getText());
        }
    }

    @FXML
    private void btnAddProduct() {
        if (textNameProduct.getText().trim().isEmpty() || textPriceProduct.getText().trim().isEmpty()) {
            alert = new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Animal Shelter | Error");
            alert.setContentText("Please fill in the name and/or price of the product");
            alert.showAndWait();
        }
        addProduct();

    }

    @FXML
    private void btnAddReservor() {
        Animal animal = (Animal) listAnimals.getSelectionModel().getSelectedItem();
        addReservor(animal);
    }


    @FXML
    private void btnSellAnimal(MouseEvent event) {
        Animal animal = (Animal) listAnimals.getSelectionModel().getSelectedItem();
        listAnimals.getItems().remove(animal);
        shop.sell(animal, sellables);
        refreshAll();
    }

    @FXML
    private void btnSellProduct() {
        Product product = (Product) listProducts.getSelectionModel().getSelectedItem();
        listProducts.getItems().remove(product);
        shop.sell(product, sellables);
        refreshAll();
    }

    private void addAnimal(String species, Gender gender, String badHabits) {
        if (species == Dog.class.getSimpleName()) {
            sellables.add(new Dog(textNameAnimal.getText(), gender));
        } else if (species == Cat.class.getSimpleName()) {
            sellables.add(new Cat(textNameAnimal.getText(), gender, badHabits));
        }
        refreshAll();
    }

    private void addProduct() {
        int price = Integer.parseInt(textPriceProduct.getText());
        sellables.add(new Product(textNameProduct.getText(), price));
        refreshAll();

    }

    private void addReservor(Animal animal) {
        animal.setReservedBy(new Reservor(textNameReservor.getText(), LocalDate.now()));
        refreshAll();
    }


    private void refreshAll() {
        labelCash.setText(String.valueOf(shop.getCashRegister()));
        listAnimals.getItems().clear();
        listProducts.getItems().clear();
        for (Sellable sellable : sellables) {
            if (sellable.getClass() == Product.class) {
                listProducts.getItems().add(sellable);
            } else if (sellable.getClass() == Dog.class) {
                listAnimals.getItems().add(sellable);
            } else if (sellable.getClass() == Cat.class) {
                listAnimals.getItems().add(sellable);
            }
        }

        textNameReservor.setText("");
        textNameAnimal.setText("");
        textBadHabits.setText("");
    }


}
