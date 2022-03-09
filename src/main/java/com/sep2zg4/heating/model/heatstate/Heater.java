package com.sep2zg4.heating.model.heatstate;

import com.sep2zg4.heating.model.TemperatureModel;

import java.util.Timer;
import java.util.TimerTask;

public class Heater
{
  private State state;
  private TemperatureModel model;
  private Timer timer;
  private TimerTask task;
  private boolean isScheduled;

  public Heater(TemperatureModel model) {
    this.state = new Off();
    this.model = model;
    this.timer = new Timer();
    this.task = new TimerTask()
    {
      @Override public void run()
      {
        prevState();
      }
    };
    this.isScheduled = false;
  }

  public State getState() {
    return state;
  }

  public void setState(State state)
  {
    this.state = state;

    if(state instanceof Off) {
      model.setPower(0);
    }
    if(state instanceof Low) {
      model.setPower(1);
    }
    if(state instanceof Medium) {
      model.setPower(2);
    }
    if(state instanceof Max) {
      model.setPower(3);
    }
  }

  public void nextState()
  {
    state.next(this);
    if(state instanceof Max) {
      isScheduled = true;
      timer.schedule(task, 5000);
    }
//    if(state instanceof Max) {
//      new java.util.Timer().schedule(new TimerTask(){
//        @Override
//        public void run() {
//          setState(new Medium());
//        }
//      },1000*5);
//    }
  }

  public void prevState()
  {
    state.prev(this);
    if(isScheduled) {
      isScheduled = false;
      task.cancel();
      task = new TimerTask()
      {
        @Override public void run()
        {
          prevState();
        }
      };
    }
  }

}
