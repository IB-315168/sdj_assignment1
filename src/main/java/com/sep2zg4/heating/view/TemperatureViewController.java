package com.sep2zg4.heating.view;

import com.sep2zg4.heating.viewmodel.TemperatureViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;

public class TemperatureViewController
{
  @FXML private Label inTemp1;
  @FXML private Label inTemp2;
  @FXML private Label outTemp;
  @FXML private Slider heaterSlider;
  @FXML private Label heaterPosition;
  @FXML private Label tempLabel1;
  @FXML private Label tempLabel2;

  private ViewHandler viewHandler;
  private TemperatureViewModel viewModel;
  private Region root;

  @FXML public void openSettings() {
    viewHandler.openView(ViewHandler.SETTINGS);
  }

  public void init(ViewHandler viewHandler, TemperatureViewModel viewModel, Region root) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    heaterSlider.valueProperty().addListener(viewModel);
    inTemp1.textProperty().bindBidirectional(
        viewModel.getInTemperature1Property());
    inTemp2.textProperty().bindBidirectional(
        viewModel.getInTemperature2Property());
    outTemp.textProperty().bindBidirectional(
        viewModel.getOutTemperatureProperty());
    heaterPosition.textProperty().bindBidirectional(
        viewModel.getHeaterPositionProperty());
    tempLabel1.textProperty().bindBidirectional(viewModel.getTemp1Property());
    tempLabel2.textProperty().bindBidirectional(viewModel.getTemp2Property());
    System.out.println(heaterSlider.getValue());
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {

  }
}
