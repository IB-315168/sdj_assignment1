package com.sep2zg4.heating.model.heatstate;

public class Medium implements State
{
  @Override public void next(Heater heater)
  {
    heater.setState(new Max());
  }

  @Override public void prev(Heater heater)
  {
    heater.setState(new Low());
  }

  @Override public String status()
  {
    return "MEDIUM";
  }
}
