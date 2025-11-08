package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.presentation.ServerFront;
import org.jetbrains.annotations.NotNull;

/**
 * Runs a Server instance
 */
public class Main {
  public static void main(String @NotNull [] args) {
    ServerFront front = new ServerFront(args[0], Integer.parseInt(args[1]));
    front.start();
  }
}