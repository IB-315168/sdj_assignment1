package com.sep2zg4.heating.viewmodel;

import com.sep2zg4.heating.model.TemperatureModel;

public class ViewModelFactory
{
  private final TemperatureViewModel temperatureViewModel;

  public ViewModelFactory(TemperatureModel model) {
    this.temperatureViewModel = new TemperatureViewModel(model);
  }

  public TemperatureViewModel getTemperatureViewModel() {
    return temperatureViewModel;
  }
}
