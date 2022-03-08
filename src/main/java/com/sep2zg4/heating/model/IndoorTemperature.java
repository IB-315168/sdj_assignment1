package com.sep2zg4.heating.model;

import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IndoorTemperature implements Runnable, PropertyChangeListener
{
  private String id;
  private double t;
  private int p;
  private int d;
  private double t0;
  private int s = 6;
  private OutdoorTemperature outTemperature;
  private TemperatureModel model;

  public IndoorTemperature(String id, double t, int d, OutdoorTemperature outTemperature, TemperatureModel model)
  {
    this.id = id;
    this.t = t;
    this.p = 0;
    this.d = d;
    this.outTemperature = outTemperature;
    this.model = model;

    model.addListener(this);
  }

  public void setPower(int p) {
    this.p = p;
  }

  private double temperature(double t, int p, int d, double t0, int s)
  {
    double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
    tMax = Math.max(Math.max(t, tMax), t0);
    double heaterTerm = 0;
    if (p > 0)
    {
      double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
      heaterTerm = 30 * s * Math.abs(tMax - t) / den;
    }
    double outdoorTerm = (t - t0) * s / 250.0;
    t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
    return t;
  }

  @Override public void run()
  {
    while(true) {
      t0 = outTemperature.getTemperature();
      t = temperature(t, p, d, t0, s);
      Platform.runLater(new Runnable()
      {
        @Override public void run()
        {
          model.addRecord(id, t);
        }
      });
      try
      {
        Thread.sleep(s * 1000L);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public String toString() {
    return id + ": " + t + "\u00B0C";
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName() == "power") {
      setPower((Integer) evt.getNewValue());
    }
  }
}
