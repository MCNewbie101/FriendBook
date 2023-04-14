module com.example.friendbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.friendbook to javafx.fxml;
    exports com.example.friendbook;
}