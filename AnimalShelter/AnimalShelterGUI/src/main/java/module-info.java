module nl.fhict {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimalShelterLogic;

    opens nl.fhict to javafx.fxml;
    exports nl.fhict;
}
