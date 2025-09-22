package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.entities.TV;

import java.io.*;
import java.net.Socket;

/**
 * Represents a server thread for handling TV-related operations.
 */
public class TVServerThread extends Thread {
  private Socket socket;
  private TV tv;

  /**
   * Instantiates a new TV server thread.
   *
   * @param socket the socket to be used by the server thread
   */
  public TVServerThread(Socket socket, TV tv) {
    super("TVServerThread");
    if (socket == null) {
      throw new IllegalArgumentException("Socket cannot be null");
    }
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.socket = socket;
    this.tv = tv;
  }

  /**
   * Runs the server thread.
   */
  @Override
  public void run() {
    try (
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()));
            ) {
      String inputLine;
      String outputLine;
      TVProtocol protocol = new TVProtocol(tv);
      inputLine = in.readLine();
      outputLine = protocol.process(Command.valueOf(inputLine));
      out.println(outputLine);
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
