package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.presentation.ServerFront;


/**
 * Runs a Server instance
 */
public class Main {
  public static void main(String[] args) {
    ServerFront front = new ServerFront("127.0.0.1", 1238);
    front.start();
  }
}