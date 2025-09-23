package ntnu.iir.bidata.martinbf.presentation;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.iir.bidata.martinbf.logic.Remote;
import ntnu.iir.bidata.martinbf.logic.TVRemoteClient;

/**
 * Main class for the Remote Application.
 */
public class RemoteApp extends Application {

  private Label channel;

  /**
   * Starts the JavaFX application.
   */
  @Override
  public void start(Stage primaryStage) {
    Remote remote = new Remote();
    RemoteController controller = new RemoteController(this,
            new TVRemoteClient(remote, 1238));
    BorderPane rootNode = new BorderPane();
    VBox middle = new VBox();
    rootNode.setTop(middle);
    channel = new Label("Channel: " + remote.getCurrentChannel());
    middle.getChildren().add(channel);
    Button powerButton = new Button("Power");
    powerButton.setOnAction(e -> controller.power());
    Button channelUpButton = new Button("Channel Up");
    channelUpButton.setOnAction(e -> controller.channelUp());
    Button channelDownButton = new Button("Channel Down");
    channelDownButton.setOnAction(e -> controller.channelDown());
    middle.getChildren().addAll(channelUpButton, channelDownButton, powerButton);
    primaryStage.setScene(new javafx.scene.Scene(rootNode, 300, 250));
    primaryStage.setTitle("TV Remote");
    primaryStage.show();
  }

  /**
   * Updates the UI.
   */
  public void update() {
  }

  public static void main(String[] args) {
    launch(args);
  }
}
