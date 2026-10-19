module org.example.taller3poo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.taller3poo to javafx.fxml;
    exports Controlador;
    exports Modelo;
    exports Vista;
}