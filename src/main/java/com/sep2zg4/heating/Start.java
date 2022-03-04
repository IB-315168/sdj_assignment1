package com.sep2zg4.heating;

import com.sep2zg4.heating.model.IndoorTemperature;
import com.sep2zg4.heating.model.OutdoorTemperature;
import com.sep2zg4.heating.model.TemperatureModel;
import com.sep2zg4.heating.model.TemperatureModelManager;
import com.sep2zg4.heating.view.ViewHandler;
import com.sep2zg4.heating.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    TemperatureModel temperatureModel = new TemperatureModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(temperatureModel);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    IndoorTemperature inTemp1 = new IndoorTemperature("it1", 15, 1, 6, temperatureModel);
    IndoorTemperature inTemp2 = new IndoorTemperature("it2", 15, 7, 6, temperatureModel);
    OutdoorTemperature outTemp = new OutdoorTemperature("ot", 6, temperatureModel);
    Thread t1 = new Thread(inTemp1);
    Thread t2 = new Thread(inTemp2);
    Thread t3 = new Thread(outTemp);
    t1.start();
    t2.start();
    t3.start();
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args) {
    launch();
  }
}
