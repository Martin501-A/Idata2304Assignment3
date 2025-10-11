package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;

import java.util.List;

/**
 * A generic parser interface for parsing byte data into objects of type T.
 *
 * @param <I> the type to decode from.
 * @param <O> the type to decode to.
 */
public interface Decoder<I, O> {

  /**
   * Parses the given byte data into an array of objects of type T.
   *
   * @param data The byte data to parse.
   * @return An array of objects of type T.
   */
   List<O> decode(I data) throws CorruptDataException;

}
