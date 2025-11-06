package ntnu.iir.bidata.martinbf.entrypoints;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.logic.TVMessage;
import ntnu.iir.bidata.martinbf.logic.client.Client;
import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.connection.ConnectionFactory;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;
import ntnu.iir.bidata.martinbf.logic.iodata.DataSender;
import ntnu.iir.bidata.martinbf.logic.iodata.RemoteCommandSender;
import ntnu.iir.bidata.martinbf.logic.iodata.RemoteHandler;
import ntnu.iir.bidata.martinbf.presentation.RemoteCommandController;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Remote Application.
 */
public class RemoteApp extends Application {

  private final TextField channelField = new TextField();
  private Remote remote;

  public static void main(String[] args) {
    // Ask for TV address and port before launching JavaFX
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter TV IP address (e.g., 127.0.0.1): ");
    String ip = scanner.nextLine();

    System.out.print("Enter TV port: ");
    int port = Integer.parseInt(scanner.nextLine());

    // Pass address/port via system properties or arguments
    System.setProperty("tv.ip", ip);
    System.setProperty("tv.port", String.valueOf(port));

    launch(args);
  }

  @Override
  public void start(Stage stage) {
    // Read address/port from system properties
    String ip = System.getProperty("tv.ip");
    int port = Integer.parseInt(System.getProperty("tv.port"));

    // Create connection(s) for the remote
    List<InetSocketAddress> addresses = new ArrayList<>();
    addresses.add(new InetSocketAddress(ip, port));

    remote = new Remote();
    channelField.setEditable(false);
    DataHandler handler = new RemoteHandler(remote);
    Client client = new Client(createConnections(addresses), handler);
    DataSender<TVMessage> sender = new RemoteCommandSender(client);
    RemoteCommandController controller = new RemoteCommandController(sender);

    // Buttons
    Button powerButton = new Button("Power");
    Button channelUpButton = new Button("Channel +");
    Button channelDownButton = new Button("Channel -");

    powerButton.setOnAction(e -> controller.sendPower());
    channelUpButton.setOnAction(e -> controller.sendChannelUp());
    channelDownButton.setOnAction(e -> controller.sendChannelDown());
    /*
    channelField.textProperty().bind(() -> {});
    */
    VBox root = new VBox(10, powerButton, channelUpButton, channelDownButton, channelField);
    root.setStyle("-fx-padding: 10; -fx-font-size: 14;");

    stage.setScene(new Scene(root, 250, 200));
    stage.setTitle("TV Remote");
    stage.show();

    // Start remote client
    client.start();
  }

  private List<Connection> createConnections(List<InetSocketAddress> addresses) {
    List<Connection> cons = new ArrayList<>();
    ConnectionFactory factory = ConnectionFactory.getInstance();
    for (InetSocketAddress address: addresses) {
      cons.add(factory.createTCPConnection(address)); //TCP or UDP here Kinda.
    }
    return cons;
  }
}
