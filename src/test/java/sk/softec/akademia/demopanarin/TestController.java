package sk.softec.akademia.demopanarin;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;

@RestController
@RequestMapping("/test")
public class TestController {

  /**
   * Throws a DataRetrievalFailureException with the message "Data access exception message".
   *
   * @throws DataAccessException if there is an error accessing data.
   */
  @GetMapping("/repositoryException")
  public void throwRepositoryException() throws DataAccessException {
    throw new DataRetrievalFailureException("Data access exception message");
  }

  /**
   * Throws a StandingOrderNotFoundException with the message "HTTP exception message".
   *
   * @throws StandingOrderNotFoundException if the standing order is not found.
   */
  @GetMapping("/NotFoundException")
  public void throwHttpException() throws StandingOrderNotFoundException {
    throw new StandingOrderNotFoundException("HTTP exception message");
  }

  /**
   * Throws a generic Exception with the message "General exception message".
   *
   * @throws Exception if there is a general error.
   */
  @GetMapping("/generalException")
  public void throwGeneralException() throws Exception {
    throw new Exception("General exception message");
  }
}
