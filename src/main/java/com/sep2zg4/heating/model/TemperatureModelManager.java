package com.sep2zg4.heating.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemperatureModelManager implements TemperatureModel
{
  private HashMap<String, ArrayList<Double>> temperatures;
  private int heaterPower;
  private double cold;
  private double hot;
  private PropertyChangeSupport support;

  public TemperatureModelManager() {
    this.temperatures = new HashMap<>();
    this.support = new PropertyChangeSupport(this);
    this.heaterPower = 0;

    temperatures.put("it1", new ArrayList<>());
    temperatures.put("it2", new ArrayList<>());
    temperatures.put("ot", new ArrayList<>());
  }

  public void addListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void addRecord(String id, double t) {
    if(getLastRecord(id) != null) {
      double old = (double) getLastRecord(id);
      temperatures.get(id).add(t);
      support.firePropertyChange(new PropertyChangeEvent(this, id, old, t));
    } else {
      temperatures.get(id).add(t);
      support.firePropertyChange(new PropertyChangeEvent(this, id, null, t));
    }
  }

  public Object getLastRecord(String id) {
    if(temperatures.get(id).size() < 1) {
      return null;
    }
    return temperatures.get(id).get(temperatures.get(id).size()-1);
  }

  @Override public ObservableList<Double> getAllRecords(String id)
  {
    return FXCollections.observableList(temperatures.get(id));
  }

  public void setPower(int p) {
    int old = heaterPower;
    heaterPower = p;
    support.firePropertyChange(new PropertyChangeEvent(this, "power", old, p));
  }

  public void setHot(double hot) {
    this.hot = hot;
  }

  public void setCold(double cold) {
    this.cold = cold;
  }

  @Override public double getHot()
  {
    return hot;
  }

  @Override public double getCold()
  {
    return cold;
  }
}
