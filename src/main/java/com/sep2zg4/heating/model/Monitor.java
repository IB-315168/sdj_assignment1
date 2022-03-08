package com.sep2zg4.heating.model;

public interface Monitor
{
  public void onTempChange(double temp);
  public String getId();
}
