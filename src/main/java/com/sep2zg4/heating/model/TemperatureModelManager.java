package com.sep2zg4.heating.model;

import com.sep2zg4.heating.model.heating.Heater;
import com.sep2zg4.heating.model.heating.Max;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemperatureModelManager implements TemperatureModel
{
  private HashMap<String, ArrayList<Double>> temperatures;
  private List<Monitor> monitors;
  private int heaterPower;
  private double cold;
  private double hot;
  private PropertyChangeSupport support;
  private Heater heater;

  public TemperatureModelManager() {
    this.temperatures = new HashMap<>();
    this.monitors = new ArrayList<>();
    this.support = new PropertyChangeSupport(this);
    this.heaterPower = 0;
    this.heater = new Heater();

    temperatures.put("it1", new ArrayList<>());
    temperatures.put("it2", new ArrayList<>());
    temperatures.put("ot", new ArrayList<>());
  }

  public void addListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public void addObserver(Monitor monitor) {
    this.monitors.add(monitor);
  }

  public void removeObserver(Monitor monitor) {
    this.monitors.remove(monitor);
  }

  public void addRecord(String id, double t) {
    if(getLastRecord(id) != null) {
      double old = (double) getLastRecord(id);
      temperatures.get(id).add(t);
      support.firePropertyChange(new PropertyChangeEvent(this, id, old, t));
      setState(id, t);
    } else {
      temperatures.get(id).add(t);
      support.firePropertyChange(new PropertyChangeEvent(this, id, null, t));
      setState(id, t);
    }
  }

  private void setState(String id, double temp) {
    for(Monitor monitor : monitors) {
      if(monitor.getId().equals(id)) {
        monitor.onTempChange(temp);
      }
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

  public Heater getHeater() {
    return heater;
  }

  public void setPower(int p)
  {
    int old = heaterPower;
    heaterPower = p;
    if(old < p) {
      heater.nextState();
    }
    if(old > p) {
      heater.prevState();
    }
    support.firePropertyChange(new PropertyChangeEvent(this, "power", old, p));
  }

  public void setHotColdValues(double hot, double cold) throws IllegalArgumentException {
    if(hot <= cold) {
      throw new IllegalArgumentException("Cold value cannot be lower than hot.");
    }

    this.hot = hot;
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
