package ntnu.iir.bidata.martinbf.logic.services.MessageResolver;

import ntnu.iir.bidata.martinbf.entity.Message;

import java.util.List;

/**
 * Resolves messages given to their respective handlers.
 */
public interface MessageHandlerService {

  /**
   * Resolves all messages in the list.
   *
   * @param messages the messages to resolve
   */
  List<Message> resolve(List<Message> messages);
}
