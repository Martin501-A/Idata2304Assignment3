package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.entity.TVSubscriber;
import ntnu.iir.bidata.martinbf.logic.TVCommandActor;
import ntnu.iir.bidata.martinbf.logic.iodata.*;
import ntnu.iir.bidata.martinbf.logic.server.Server;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * Represents some visual for the Server as well as a way to start it.
 */
public class ServerFront implements TVSubscriber {
  private final TV tv;
  private final Server server;

  /**
   * Server obj.
   */
  public ServerFront(String host, int port) {
    try {
      this.tv = new TV(Arrays.stream(Channel.values()).toList());
      TVCommandActor actor = new TVCommandActor(tv);
      DataHandler handler = new TVHandler(actor);
      this.server = new Server(new InetSocketAddress(host, port), handler);
      DataSender<Channel> sender = new ChannelSender(tv, server);
      tv.subscribe(this);
    } catch  (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  /**
   * starts the server.
   */
  public void start() {
    this.server.start();
    update();
  }


  @Override
  public void update() {
    System.out.println("Power: " + tv.getPowerStatus()
            + "| Channel: " + tv.getCurrentChannel());
  }
}
