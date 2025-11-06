package ntnu.iir.bidata.martinbf.logic.iodata;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;

import java.util.Objects;

public record IOEvent(Type type, EventHandler handler) {
  public IOEvent {
    Objects.requireNonNull(type, "type is null");
    Objects.requireNonNull(handler, "handler is null");
  }

  public enum Type {
    INCOMING,
    OUTGOING
  }
}
