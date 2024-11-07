module org.example.javafx2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.javafx2 to javafx.fxml;
    exports org.example.javafx2;
}