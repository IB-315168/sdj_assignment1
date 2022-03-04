package com.sep2zg4.heating.viewmodel;

import com.sep2zg4.heating.model.TemperatureModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsViewModel implements PropertyChangeListener
{
  private TemperatureModel model;
  private StringProperty hotField;
  private StringProperty coldField;
  private ListProperty inTemperatureList1;
  private ListProperty inTemperatureList2;
  private ListProperty outTemperatureList;


  public SettingsViewModel(TemperatureModel model) {
    this.model = model;

    model.addListener(this);
    hotField = new SimpleStringProperty("");
    coldField = new SimpleStringProperty("");
    inTemperatureList1 = new SimpleListProperty();
    inTemperatureList2 = new SimpleListProperty();
    outTemperatureList = new SimpleListProperty();
  }

  public void setHot() {
    model.setHot(Double.parseDouble(hotField.get()));
  }

  public void setCold() {
    model.setCold(Double.parseDouble(coldField.get()));
  }

  public void getValues() {
    hotField.set(String.valueOf(model.getHot()));
    coldField.set(String.valueOf(model.getCold()));
    inTemperatureList1.set(model.getAllRecords("it1"));
    inTemperatureList2.set(model.getAllRecords("it2"));
    outTemperatureList.set(model.getAllRecords("ot"));
  }

  public StringProperty getHotField() { return hotField; }
  public StringProperty getColdField() { return coldField; }

  public ListProperty getInTemperatureList1()
  {
    return inTemperatureList1;
  }

  public ListProperty getInTemperatureList2() {
    return inTemperatureList2;
  }

  public ListProperty getOutTemperatureList()
  {
    return outTemperatureList;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(new Runnable()
    {
      @Override public void run()
      {
        getValues();
      }
    });
  }
}
