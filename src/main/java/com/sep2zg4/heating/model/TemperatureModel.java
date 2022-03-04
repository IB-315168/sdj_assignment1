package com.sep2zg4.heating.model;

import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface TemperatureModel
{
  public void addListener(PropertyChangeListener listener);
  public void removeListener(PropertyChangeListener listener);
  public void addRecord(String id, double t);
  public Object getLastRecord(String id);
  public ObservableList<Double> getAllRecords(String id);
  public void setPower(int p);
  public void setHot(double hot);
  public void setCold(double cold);
  public double getHot();
  public double getCold();
}
