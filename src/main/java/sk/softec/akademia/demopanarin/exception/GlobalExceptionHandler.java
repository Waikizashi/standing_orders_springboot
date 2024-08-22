package sk.softec.akademia.demopanarin.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler that catches specific exceptions and returns appropriate error responses
 * with custom error messages.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles DataAccessException and returns an Internal Server Error response with a custom error
   * message.
   *
   * @param ex The exception being caught.
   * @return A ResponseEntity containing a CustomErrorType object with an error message and details,
   * and a 500 status code.
   */
  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<Object> handleRepositoryException(DataAccessException ex) {
    CustomErrorType error = new CustomErrorType("Repository Error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles StandingOrderNotFoundException and returns a Bad Request response with a custom error
   * message.
   *
   * @param ex The exception being caught.
   * @return A ResponseEntity containing a CustomErrorType object with an error message and details,
   * and a 400 status code.
   */
  @ExceptionHandler(StandingOrderNotFoundException.class)
  public ResponseEntity<Object> StandingOrderNotFoundException(StandingOrderNotFoundException ex) {
    CustomErrorType error = new CustomErrorType("HTTP Error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles all other exceptions and returns an Internal Server Error response with a custom error
   * message.
   *
   * @param ex The exception being caught.
   * @return A ResponseEntity containing a CustomErrorType object with an error message and details,
   * and a 500 status code.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllExceptions(Exception ex) {
    CustomErrorType error = new CustomErrorType("General Error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
