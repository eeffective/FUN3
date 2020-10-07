package nl.fhict;

import java.io.IOException;
import javafx.fxml.FXML;
import nl.fhict.classes.Animal;
import nl.fhict.classes.Dog;
import nl.fhict.enums.Gender;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("secondary");
    }

    void smd(){

    }
}
