package ntnu.iir.bidata.martinbf.logic.services.MessageResolver;

import ntnu.iir.bidata.martinbf.entity.Message;

import java.util.List;

/**
 * Resolves messages given to their respective handlers.
 */
public interface MessageResolverService {

  /**
   * Resolves all messages in the list.
   *
   * @param messages the messages to resolve
   */
  void resolve(List<Message> messages);

  /**
   * Returns the responses of the resolved messages.
   */
  List<Message> getResponses();
}
