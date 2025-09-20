package ntnu.iir.bidata.martinbf.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Tests for CircularIterator.</p>
 *
 * <p>The Following is tested:</p>
 *
 * <p>Positive tests</p>
 *
 * <ul>
 *   <li>That a valid list can be iterated over</li>
 *   <li>next() method wraps around to the beginning</li>
 *   <li>previous() method wraps around to the end</li>
 * </ul>
 *
 * <p>Negative tests</p>
 *
 * <ul>
 *   <li>That an invalid list can be handled</li>
 *   <li>That it does not run forever in a for loop except given permission</li>
 * </ul>
 */
class CircularIteratorTest {

  // Positive tests

  /**
   * Test that the next() method correctly iterates over the list and wraps around.
   */
  @Test
  public void thatNextIteratesCorrectly() {
    CircularIterator<Integer> iterator = new CircularIterator<>(List.of(1, 2, 3));
    assertEquals(1, iterator.current());
    assertEquals(2, iterator.next());
    assertEquals(3, iterator.next());
    // Wrap around
    assertEquals(1, iterator.next());
  }

  /**
   * Test that the previous() method correctly iterates over the list in reverse and wraps around.
   */
  @Test
  public void thatPreviousIteratesCorrectly() {
    CircularIterator<Integer> iterator = new CircularIterator<>(List.of(1, 2, 3));
    assertEquals(3, iterator.previous());
    assertEquals(2, iterator.previous());
    assertEquals(1, iterator.previous());
    // Wrap around
    assertEquals(3, iterator.previous());
  }

  // Negative tests

  /**
   * Test that creating a CircularIterator with a null list throws an IllegalArgumentException.
   */
  @Test
  public void thatNullListThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new CircularIterator<>(null));
  }

  /**
   * Test that creating a CircularIterator with an empty list throws an IllegalArgumentException.
   */
  @Test
  public void thatEmptyListThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new CircularIterator<>(List.of()));
  }

  /**
   * Test that the iterator does not run forever in a for loop without permission.
   */
  @Test
  public void thatIteratorDoesNotRunForeverWithoutPermission() {
    try {
      CircularIterator<Integer> iterator = new CircularIterator<>(List.of(1, 2, 3));
      for (int i = 0; i < 10; i++) {
        iterator.next();
      }
      fail();
    } catch (IllegalIterationException e) {
      assertTrue(true);
    }
  }
}