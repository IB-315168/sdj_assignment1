package com.sep2zg4.heating.view;

import com.sep2zg4.heating.viewmodel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  public static final String WINDOW1 = "window1";
  public static final String WINDOW2 = "window2";

  private final Scene currentScene;
  private Stage primaryStage;
  private final ViewFactory viewFactory;

  public ViewHandler(ViewModelFactory viewModelFactory) {
    this.viewFactory = new ViewFactory(this, viewModelFactory);
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView(window1);
  }

  public void openView(String id) {
    Region root = switch(id) {
//      case CREATE -> viewFactory.loadCreateAccountView();
//      case MESSAGE -> viewFactory.loadMessageView();
      default -> throw new IllegalArgumentException("Unknown view: " + id);
    };
    currentScene.setRoot(root);
    if (root.getUserData() == null) {
      primaryStage.setTitle("");
    } else {
      primaryStage.setTitle(root.getUserData().toString());
    }
    primaryStage.setScene(currentScene);
    primaryStage.sizeToScene();
    primaryStage.show();
  }

  public void closeView() {
    primaryStage.close();
  }
}
