package ntnu.iir.bidata.martinbf.presentation;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.logic.client.TCPRemoteClient;
import ntnu.iir.bidata.martinbf.logic.client.UDPRemoteClient;

/**
 * Main class for the Remote Application.
 */
public class RemoteApp extends Application {

  private Label channel;
  private Remote remote;

  /**
   * Starts the JavaFX application.
   */
  @Override
  public void start(Stage primaryStage) {
    this.remote = new Remote();
    RemoteController controller = new RemoteController(this,
            new UDPRemoteClient(remote, 1238));
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
    this.channel.setText("Channel: " + remote.getCurrentChannel());
  }

  public static void main(String[] args) {
    launch(args);
  }
}
