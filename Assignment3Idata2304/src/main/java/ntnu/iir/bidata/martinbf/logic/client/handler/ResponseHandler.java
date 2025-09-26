package ntnu.iir.bidata.martinbf.logic.client.handler;

/**
 * Handles responses for the client.
 */
public interface ResponseHandler {

  /**
   * Handles a response
   */
  public void handle(String response) throws Exception;
}
