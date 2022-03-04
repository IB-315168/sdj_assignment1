package com.sep2zg4.heating.model;

import java.beans.PropertyChangeListener;

public interface TemperatureModel
{
  public void addListener(PropertyChangeListener listener);
  public void removeListener(PropertyChangeListener listener);
  public void addRecord(String id, double t);
  public Object getLastRecord(String id);
  public void setPower(int p);
}
