package ntnu.iir.bidata.martinbf.presentation;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ntnu.iir.bidata.martinbf.entity.Remote;

/**
 * Main class for the Remote Application.
 */
public class RemoteApp extends Application {

  private Label channel;
  private Remote remote;
  private int port;

  /**
   * Starts the JavaFX application.
   */
  @Override
  public void start(Stage primaryStage) {
  }
  public static void main(String[] args) {
    launch(args);
  }
}
