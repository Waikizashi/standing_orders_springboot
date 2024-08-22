package sk.softec.akademia.demopanarin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import sk.softec.akademia.demopanarin.exception.GlobalExceptionHandler;
import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the {@link GlobalExceptionHandler} class.
 */
@WebMvcTest(TestController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTests {

  @Autowired
  private MockMvc mockMvc;

  /**
   * Tests that the {@link GlobalExceptionHandler#handleRepositoryException(DataAccessException)}
   * method returns an internal server error status.
   */
  @Test
  void testHandleRepositoryException() throws Exception {
    mockMvc.perform(get("/test/repositoryException"))
        .andExpect(status().isInternalServerError());
  }

  /**
   * Tests that the
   * {@link GlobalExceptionHandler#StandingOrderNotFoundException(StandingOrderNotFoundException)}
   * method returns a bad request status.
   */
  @Test
  void testHandleHttpException() throws Exception {
    mockMvc.perform(get("/test/NotFoundException"))
        .andExpect(status().isBadRequest());
  }

  /**
   * Tests that the {@link GlobalExceptionHandler#handleAllExceptions(Exception)} method returns an
   * internal server error status.
   */
  @Test
  void testHandleGeneralException() throws Exception {
    mockMvc.perform(get("/test/generalException"))
        .andExpect(status().isInternalServerError());
  }
}


