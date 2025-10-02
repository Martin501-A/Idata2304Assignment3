package ntnu.iir.bidata.martinbf.logic.connection;

/**
 * Represents a factory for creating connection instances.
 */
public interface ConnectionFactory {

  /**
   * Creates and returns a new Connection instance.
   *
   * @return a new Connection instance
   */
  Connection getConnection();
}
