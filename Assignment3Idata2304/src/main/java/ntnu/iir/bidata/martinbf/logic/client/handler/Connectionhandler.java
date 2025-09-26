package ntnu.iir.bidata.martinbf.logic.client.handler;

/**
 * Handles connection Response for the client.
 */
public class Connectionhandler implements ResponseHandler {

  private String socketAddress;

  /**
   * Instantiates a new Connectionhandler.
   */
  public Connectionhandler() {
    this.socketAddress = null;
  }

  /**
   * Handles a response
   *
   * @param response the response
   * @throws Exception if response is null
   */
  @Override
  public void handle(String response) throws Exception {
    if (response == null) {
      throw new Exception(); //TODO Make custom exception.
    }
  }
}
