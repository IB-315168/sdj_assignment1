package com.sep2zg4.heating.model;

import com.sep2zg4.heating.model.heatstate.Heater;
import com.sep2zg4.heating.viewmodel.TemperatureViewModel;
import javafx.beans.property.DoubleProperty;
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
    this.heater = new Heater(this);

    temperatures.put("it1", new ArrayList<>());
    temperatures.put("it2", new ArrayList<>());
    temperatures.put("ot", new ArrayList<>());
  }

  @Override public void addListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  @Override public void addObserver(Monitor monitor) {
    this.monitors.add(monitor);
  }

  @Override public void removeObserver(Monitor monitor) {
    this.monitors.remove(monitor);
  }

  @Override public void addRecord(String id, double t) {
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

  @Override public Object getLastRecord(String id) {
    if(temperatures.get(id).size() < 1) {
      return null;
    }
    return temperatures.get(id).get(temperatures.get(id).size()-1);
  }

  @Override public ObservableList<Double> getAllRecords(String id)
  {
    return FXCollections.observableList(temperatures.get(id));
  }

  @Override public Heater getHeater() {
    return heater;
  }

  @Override public void setPower(int p)
  {
    StackWalker walker = StackWalker
        .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    int old = heaterPower;
    heaterPower = p;
    if(walker.getCallerClass().getName().equals(TemperatureViewModel.class.getName())) {
      if(old < p) {
        heater.nextState();
        System.out.println(heater.getState().status());
      }
      if(old > p) {
        heater.prevState();
        System.out.println(heater.getState().status());
      }
    }
    support.firePropertyChange(new PropertyChangeEvent(this, "power", old, p));
  }

  public Double getPower() {
    return (double) heaterPower;
  }

  @Override public void setHotColdValues(double hot, double cold) throws IllegalArgumentException {
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
