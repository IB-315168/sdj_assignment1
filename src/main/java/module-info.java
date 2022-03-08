module com.sep2zg4 {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires java.desktop;

  opens com.sep2zg4.heating to javafx.fxml;
  opens com.sep2zg4.heating.view to javafx.fxml;
  exports com.sep2zg4.heating.viewmodel;
  exports com.sep2zg4.heating.model;
  exports com.sep2zg4.heating;
  exports com.sep2zg4.heating.model.heating;
}