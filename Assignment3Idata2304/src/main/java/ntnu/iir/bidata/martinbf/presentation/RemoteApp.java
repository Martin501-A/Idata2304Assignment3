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

import java.util.Scanner;

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
    System.out.println("Starting RemoteApp");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter port of remote Server(1024-65535): ");
    int port = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter IP address: ");
    String ip = scanner.nextLine();
    this.remote = new Remote();
    RemoteController controller = new RemoteController(this,
            new UDPRemoteClient(remote, port, ip));
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
