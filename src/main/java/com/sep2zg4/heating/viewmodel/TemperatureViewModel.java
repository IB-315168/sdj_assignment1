package com.sep2zg4.heating.viewmodel;

import com.sep2zg4.heating.model.TempMonitor;
import com.sep2zg4.heating.model.TemperatureModel;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements ChangeListener,
    PropertyChangeListener
{
  private TemperatureModel model;
  private StringProperty heaterPosition;
  private StringProperty inTemperature1;
  private StringProperty inTemperature2;
  private StringProperty outTemperature;
  private StringProperty stateTemp1;
  private StringProperty stateTemp2;
  private DoubleProperty heaterValue;
  private TempMonitor tempMonitor1;
  private TempMonitor tempMonitor2;

  public TemperatureViewModel(TemperatureModel model) {
    this.model = model;
    this.tempMonitor1 = new TempMonitor(model, "it1");
    this.tempMonitor2 = new TempMonitor(model, "it2");

    model.addListener(this);

    heaterPosition = new SimpleStringProperty("OFF");
    heaterValue = new SimpleDoubleProperty(0);
    inTemperature1 = new SimpleStringProperty("");
    inTemperature2 = new SimpleStringProperty("");
    outTemperature = new SimpleStringProperty("");
    stateTemp1 = new SimpleStringProperty("");
    stateTemp2 = new SimpleStringProperty("");

    model.addObserver(tempMonitor1);
    model.addObserver(tempMonitor2);
    stateTemp1.bindBidirectional(tempMonitor1.getReading());
    stateTemp2.bindBidirectional(tempMonitor2.getReading());

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

  public StringProperty getTemp1Property()
  {
    return stateTemp1;
  }

  public StringProperty getTemp2Property() { return stateTemp2; }

  public DoubleProperty getHeaterValue() { return heaterValue; }

  @Override public void changed(ObservableValue observableValue, Object o,
      Object t1)
  {
    model.setPower((int) Math.round((Double) t1));
    heaterPosition.set(model.getHeater().getState().status());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(new Runnable()
    {
      @Override public void run()
      {
        if(evt.getPropertyName().equals("power")) {
          heaterPosition.set(model.getHeater().getState().status());
          heaterValue.set(model.getPower());
        }
        else {
          getValue(evt.getPropertyName());
        }
      }
    });
  }
}
