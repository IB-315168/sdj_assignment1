package com.sep2zg4.heating.model.heating;

import java.beans.PropertyChangeSupport;

public class Heater
{
  private State state;

  public Heater() {
    this.state = new Off();
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void nextState()
  {
    state.next(this);
  }

  public void prevState()
  {
    state.prev(this);
  }

}
