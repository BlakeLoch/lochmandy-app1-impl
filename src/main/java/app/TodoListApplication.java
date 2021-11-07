/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;


import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TodoListApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("Application.fxml")));

    Scene scene = new Scene(root);

    // set title to "To-Do List"
    stage.setTitle("To-Do List");
    // set scene
    stage.setScene(scene);
    // set minimum width to 605
    stage.setMinWidth(605);
    // set minimum height to 430
    stage.setMinHeight(430);
    // show stage
    stage.show();
  }

}