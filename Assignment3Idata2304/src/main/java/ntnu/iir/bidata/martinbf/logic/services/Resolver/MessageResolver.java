package ntnu.iir.bidata.martinbf.logic.services.Resolver;

import ntnu.iir.bidata.martinbf.entity.Message;

import java.util.List;

/**
 * Resolves messages and returns responses.
 */
public interface MessageResolver {


  /**
   * Handles a list of messages and returns responses.
   *
   * @param messages the messages to handle.
   * @return the responses for the messages.
   */
  List<Message> resolveMessages(List<Message> messages);
}
