package ntnu.iir.bidata.martinbf.logic.services.Resolver;

import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.services.decoder.DecoderRegistry;

import java.rmi.registry.Registry;
import java.util.List;

/**
 * Resolves TV messages to the TV and returns a response.
 */
public class TVMessageResolver implements MessageResolver {
  private final DecoderRegistry decoderRegistry;

  public TVMessageResolver() {
  }

  @Override
  public List<Message> resolveMessages(List<Message> messages) {
    for  (Message message : messages) {

    }
  }
}
