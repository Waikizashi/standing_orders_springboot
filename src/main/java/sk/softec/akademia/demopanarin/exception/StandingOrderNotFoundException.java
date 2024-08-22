package sk.softec.akademia.demopanarin.exception;

/**
 * A custom exception class that represents an exception thrown when a standing order is not found.
 * This exception is used to handle errors that occur when trying to access or manipulate a standing
 * order that does not exist.
 */
public class StandingOrderNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code StandingOrderNotFoundException} with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *                {@link #getMessage()} method.
   */
  public StandingOrderNotFoundException(String message) {
    super(message);
  }

}