package com.sep2zg4.heating.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TempMonitor implements Monitor
{
  private TemperatureModel model;
  private StringProperty reading;
  private String id;

  public TempMonitor(TemperatureModel model, String id) {
    this.model = model;
    this.id = id;
    this.reading = new SimpleStringProperty("");
  }

  public void onTempChange(double temp) {
    if(temp < model.getCold()) {
      reading.set("WARNING: " + id + " Temperature too low");
    }

    if(temp > model.getHot()) {
      reading.set("WARNING: "+ id +" Temperature too high");
    }

    if(temp < model.getHot() && temp > model.getCold()) {
      reading.set(id + " Temperature OK");
    }
  }

  public String getId() {
    return id;
  }

  public StringProperty getReading() {
    return reading;
  }
}
