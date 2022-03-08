package com.sep2zg4.heating.model.heating;

public class Low implements State
{
  @Override public void next(Heater heater)
  {
    heater.setState(new Medium());
  }

  @Override public void prev(Heater heater)
  {
    heater.setState(new Off());
  }

  @Override public String status()
  {
    return "LOW";
  }
}
