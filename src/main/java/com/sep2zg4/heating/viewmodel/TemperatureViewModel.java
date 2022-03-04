package com.sep2zg4.heating.viewmodel;

import com.sep2zg4.heating.model.TemperatureModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureViewModel implements ChangeListener,
    PropertyChangeListener
{
  private TemperatureModel model;
  private StringProperty heaterPosition;
  private StringProperty inTemperature1;
  private StringProperty inTemperature2;
  private StringProperty outTemperature;
  private StringProperty error;

  public TemperatureViewModel(TemperatureModel model) {
    this.model = model;

    model.addListener(this);
    heaterPosition = new SimpleStringProperty("OFF");
    inTemperature1 = new SimpleStringProperty("");
    inTemperature2 = new SimpleStringProperty("");
    outTemperature = new SimpleStringProperty("");
    error = new SimpleStringProperty("");
  }

  public void getValue(String id) {
    switch (id) {
      case "it1" -> {
        inTemperature1.set(String.valueOf(model.getLastRecord(id)));
        break;
      }
      case "it2" -> {
        inTemperature2.set(String.valueOf(model.getLastRecord(id)));
        break;
      }
      case "ot" -> {
        outTemperature.set(String.valueOf(model.getLastRecord(id)));
        break;
      }
    }
  }

  public String getHeaterPosition(int id) {
    switch (id) {
      case 0 -> { return "OFF"; }
      case 1 -> { return "LOW"; }
      case 2 -> { return "MEDIUM"; }
      case 3 -> { return "MAX"; }
    }
    return "Unknown";
  }

  public StringProperty getHeaterPositionProperty() {
    return heaterPosition;
  }

  public StringProperty getInTemperature1Property()
  {
    return inTemperature1;
  }

  public StringProperty getInTemperature2Property()
  {
    return inTemperature2;
  }

  public StringProperty getOutTemperatureProperty()
  {
    return outTemperature;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  @Override public void changed(ObservableValue observableValue, Object o,
      Object t1)
  {
    heaterPosition.set(getHeaterPosition((int) Math.round((Double) t1)));
    model.setPower((int) Math.round((Double) t1));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(new Runnable()
    {
      //TODO is it actually a good idea to use id as propertyname?
      @Override public void run()
      {
        getValue(evt.getPropertyName());
      }
    });
  }
}
