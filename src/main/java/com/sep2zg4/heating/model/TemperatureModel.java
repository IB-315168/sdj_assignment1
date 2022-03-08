package com.sep2zg4.heating.model;

import com.sep2zg4.heating.model.heating.Heater;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;

public interface TemperatureModel
{
  public void addListener(PropertyChangeListener listener);
  public void removeListener(PropertyChangeListener listener);
  public void addObserver(Monitor monitor);
  public void removeObserver(Monitor monitor);
  public void addRecord(String id, double t);
  public Object getLastRecord(String id);
  public ObservableList<Double> getAllRecords(String id);
  public Heater getHeater();
  public void setPower(int p) throws InterruptedException;
  public void setHotColdValues(double hot, double cold);
  public double getHot();
  public double getCold();
}
