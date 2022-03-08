package com.sep2zg4.heating.model;

public class OutdoorTemperature implements Runnable
{
  private final String id;
  private double t0;
  private TemperatureModel model;

  public OutdoorTemperature(String id, double t0, TemperatureModel model)
  {
    this.id = id;
    this.t0 = t0;
    this.model = model;
  }

  private void externalTemperature(double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
  }

  public synchronized double getTemperature()
  {
    return t0;
  }

  @Override public void run() {
    while(true) {
      externalTemperature(-20.0, 20.0);
      model.addRecord(id, t0);
      try
      {
        Thread.sleep(6000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public String toString() {
    return id + ": " + t0 + "\u00B0C";
  }
}
