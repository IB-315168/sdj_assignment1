package com.sep2zg4.heating.view;

import com.sep2zg4.heating.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;
  private TemperatureViewController temperatureViewController;
  private SettingsViewController settingsViewController;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.temperatureViewController = null;
    this.settingsViewController = null;
  }

  public Region loadTemperatureView() {
    if (temperatureViewController == null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("TemperatureView.fxml"));
      try {
        Region root = loader.load();
        temperatureViewController = loader.getController();
        temperatureViewController.init(viewHandler, viewModelFactory.getTemperatureViewModel()  , root);
      } catch (IOException e) {
        throw new IOError(e);
      }
    }
    temperatureViewController.reset();
    return temperatureViewController.getRoot();
  }

  public Region loadSettingsView() {
    if (settingsViewController == null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SettingsView.fxml"));
      try {
        Region root = loader.load();
        settingsViewController = loader.getController();
        settingsViewController.init(viewHandler, viewModelFactory.getSettingsViewModel()  , root);
      } catch (IOException e) {
        throw new IOError(e);
      }
    }
    settingsViewController.reset();
    return settingsViewController.getRoot();
  }
}
