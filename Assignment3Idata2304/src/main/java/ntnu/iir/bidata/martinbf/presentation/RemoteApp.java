package ntnu.iir.bidata.martinbf.presentation;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.logic.client.RemoteClient;
import ntnu.iir.bidata.martinbf.logic.client.handler.RemoteHandler;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.connectionprotocols.ConnectionProtocol;
import ntnu.iir.bidata.martinbf.logic.connectionprotocols.TCPServerFinderProtocol;
import ntnu.iir.bidata.martinbf.logic.connectionprotocols.UDPServerFinderProtocol;

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
    int port = getPortFromConsole();
    this.remote = new Remote();
    ResponseHandler handler = new RemoteHandler(this.remote);
    ConnectionProtocol protocol = getProtocolFromUser();
    RemoteController controller = new RemoteController(this,
            new RemoteClient(port, handler, protocol));
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
   * Returns the protocol chosen by the user.
   */
  private ConnectionProtocol getProtocolFromUser() {
    Scanner scanner = new Scanner(System.in);
      System.out.print("Choose protocol (UDPServerFinderProtocol, TCPServerFinderProtocol): ");
      String input = scanner.nextLine();
      ConnectionProtocol protocol;
      if (input.equals("UDPServerFinderProtocol")) {
        protocol = new TCPServerFinderProtocol();
      } else if (input.equals("TCPServerFinderProtocol")) {
        protocol = new UDPServerFinderProtocol();
      } else {
        System.out.println("Invalid input. Please enter 1 or 2.");
        scanner.close();
        protocol = getProtocolFromUser();
    }
      return protocol;
  }

  /**
   * Handles getting userInput for port number.
   */
  public static int getPortFromConsole() {
    int port = 1238;
    Scanner scanner = new Scanner(System.in);
    boolean success = false;
    while (!success) {
      System.out.print("Enter port number of remote server (1024-65535)" +
              " or press Enter for default (1238): ");
      String input = scanner.nextLine();
      if (input.isEmpty()) {
        success = true;
      } else {
        try {
          port = Integer.parseInt(input);
          if (port >= 1024 && port <= 65535) {
            success = true;
          } else {
            System.out.println("Port must be between 1024 and 65535.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a valid port number.");
        }
      }
    }
    return port;
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
