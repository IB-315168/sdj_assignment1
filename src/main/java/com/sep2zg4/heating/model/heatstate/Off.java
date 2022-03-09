package com.sep2zg4.heating.model.heatstate;

public class Off implements State
{
  @Override public void next(Heater heater)
  {
    heater.setState(new Low());
  }

  @Override public void prev(Heater heater)
  {
    heater.setState(new Max());
  }

  @Override public String status()
  {
    return "OFF";
  }
}
