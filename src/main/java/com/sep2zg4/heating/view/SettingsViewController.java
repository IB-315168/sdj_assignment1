package com.sep2zg4.heating.view;

import com.sep2zg4.heating.viewmodel.SettingsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SettingsViewController
{
  @FXML private TextField hotField;
  @FXML private TextField coldField;
  @FXML private ListView inTemperatureListView1;
  @FXML private ListView inTemperatureListView2;
  @FXML private ListView outTemperatureListView;

  private ViewHandler viewHandler;
  private SettingsViewModel viewModel;
  private Region root;

  @FXML public void saveSettings() {
    viewModel.getValues();
  }

  public void init(ViewHandler viewHandler, SettingsViewModel viewModel, Region root) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.hotField.textProperty().bindBidirectional(viewModel.getHotField());
    this.coldField.textProperty().bindBidirectional(viewModel.getColdField());
    this.inTemperatureListView1.itemsProperty().bindBidirectional(viewModel.getInTemperatureList1());
    this.inTemperatureListView2.itemsProperty().bindBidirectional(viewModel.getInTemperatureList2());
    this.outTemperatureListView.itemsProperty().bindBidirectional(viewModel.getOutTemperatureList());
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {

  }
}
