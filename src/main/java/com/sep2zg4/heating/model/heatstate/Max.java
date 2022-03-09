package com.sep2zg4.heating.model.heatstate;

public class Max implements State
{
  @Override public void next(Heater heater)
  {
    heater.setState(new Off());
  }

  @Override public void prev(Heater heater)
  {
    heater.setState(new Medium());
  }

  @Override public String status()
  {
    return "MAX";
  }
}
