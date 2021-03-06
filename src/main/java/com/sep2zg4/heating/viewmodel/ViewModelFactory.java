package com.sep2zg4.heating.viewmodel;

import com.sep2zg4.heating.model.TemperatureModel;

public class ViewModelFactory
{
  private final TemperatureViewModel temperatureViewModel;
  private final SettingsViewModel settingsViewModel;

  public ViewModelFactory(TemperatureModel model) {
    this.temperatureViewModel = new TemperatureViewModel(model);
    this.settingsViewModel = new SettingsViewModel(model);
  }

  public TemperatureViewModel getTemperatureViewModel() {
    return temperatureViewModel;
  }

  public SettingsViewModel getSettingsViewModel()
  {
    return settingsViewModel;
  }
}
