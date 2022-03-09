package com.sep2zg4.heating.model.heatstate;

public interface State
{
  public void next(Heater heater);
  public void prev(Heater heater);
  public String status();
}
