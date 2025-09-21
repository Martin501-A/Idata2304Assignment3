package ntnu.iir.bidata.martinbf.datatypes;

import ntnu.iir.bidata.martinbf.exception.IllegalIterationException;

import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;


/**
 * An iterator that loops indefinitely over a collection. With pointer to current, prev and next.
 */
public class CircularIterator<T> implements Iterator<T> {
  private final List<T> list;
  private int currentIndex;
  private boolean infiniteLoopPermission;
  private int iteratedCount;
  private final int maxIterations; // Safety limit to prevent infinite loops

  /**
   * Create a CircularIterator from a list.
   * Sets a loop limit equal to 2 times the size of the list to prevent infinite loops.
   */
  public CircularIterator(List<T> list) {
    if (list == null) {
      throw new IllegalArgumentException("List cannot be null");
    }
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List cannot be empty");
    }
    this.list = list;
    this.currentIndex = 0;
    this.infiniteLoopPermission = false;
    this.iteratedCount = 0;
    this.maxIterations = list.size() * 2; // Set a safety limit
  }

 /**
   * Always returns true, as this iterator is circular.
   * Also use if iterating backwards.
   * However, if infinite looping is not allowed, it will throw an exception if the maximum
   * number of iterations is exceeded.
   */
  @Override
  public boolean hasNext() {
    if (!infiniteLoopPermission) {
      if (abs(iteratedCount) > maxIterations) {
        throw new IllegalIterationException("Exceeded maximum iterations. " +
                "To allow infinite looping, call allowInfiniteLooping().");
      }
    }
    return true; // Always has a next element in a circular iterator
  }

  /**
   * Allow infinite looping. Use with caution.
   */
  public void allowInfiniteLooping() {
    this.infiniteLoopPermission = true;
  }

  /**
   * Returns the next element in the iteration, wrapping around to the beginning if necessary.
   *
   * @return the next element in the iteration.
   */
  @Override
  public T next() {
    iteratedCount++;
    isInfiniteLoopingAllowed();
    this.currentIndex = (this.currentIndex + 1) % this.list.size();
    return this.list.get(this.currentIndex);
  }

  /**
   * Returns the previous element in the iteration, wrapping around to the end if necessary.
   */
  public T previous() {
    iteratedCount--;
    isInfiniteLoopingAllowed();
    this.currentIndex = (this.currentIndex - 1 + this.list.size()) % this.list.size();
    return this.list.get(this.currentIndex);
  }

  /**
   * Checks if the iterator is allowed to loop infinitely.
   *
   * @throws IllegalIterationException if infinite looping is not allowed and the maximum
   *                                   number of iterations is exceeded.
   */
  public void isInfiniteLoopingAllowed() {
    if (!infiniteLoopPermission) {
      if (abs(iteratedCount) > maxIterations) {
        throw new IllegalIterationException("Exceeded maximum iterations. " +
                "To allow infinite looping, call allowInfiniteLooping().");
      }
    }
  }

  /**
   * Returns the current element in the iteration.
   */
  public T current() {
    return this.list.get(this.currentIndex);
  }

}

