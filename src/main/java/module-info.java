module com.sep2zg4 {
  requires javafx.graphics;

  opens com.sep2zg4.heating.view to javafx.fxml;
  opens com.sep2zg4.heating to javafx.fxml;

  exports com.sep2zg4.heating.view;
  exports com.sep2zg4.heating.viewmodel;
  exports com.sep2zg4.heating.model;
  exports com.sep2zg4.heating;
}