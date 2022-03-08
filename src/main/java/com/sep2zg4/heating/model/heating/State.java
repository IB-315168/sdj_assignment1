package com.sep2zg4.heating.model.heating;

public interface State
{
  public void next(Heater heater);
  public void prev(Heater heater);
  public String status();
}
