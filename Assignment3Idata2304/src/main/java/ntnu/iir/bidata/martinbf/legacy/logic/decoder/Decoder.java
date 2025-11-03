package ntnu.iir.bidata.martinbf.legacy.logic.decoder;

import ntnu.iir.bidata.martinbf.legacy.logic.CorruptDataException;

/**
 * A generic parser interface for parsing byte data into objects of type T.
 *
 * @param <I> the type to decode from.
 * @param <O> the type to decode to.
 * @author martin barth frøseth
 */
public interface Decoder<I, O> {

  /**
   * Parses the given byte data into an array of objects of type T.
   *
   * @param data The byte data to parse.
   * @return An array of objects of type T.
   * @throws CorruptDataException if the data has something unexpected in it.
   */
   O decode(I data) throws CorruptDataException;

}
