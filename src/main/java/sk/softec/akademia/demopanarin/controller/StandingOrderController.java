package sk.softec.akademia.demopanarin.controller;

import jakarta.validation.Valid;
import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;
import java.time.LocalDate;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.service.StandingOrderService;

/**
 * REST controller for handling CRUD operations for standing orders. This controller provides
 * endpoints for retrieving, creating, updating and deleting standing orders. The controller relies
 * on the {@link StandingOrderService} to perform the necessary business logic.
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/standing-orders")
public class StandingOrderController {

  private StandingOrderService standingOrderService;

  /**
   * Endpoint for retrieving all standing orders. Returns a list of {@link StandingOrderDtoDetails}
   * objects.
   *
   * @return ResponseEntity with the list of standing orders and an HTTP status code.
   */
  @GetMapping
  public ResponseEntity<List<StandingOrderDtoDetails>> getAllStandingOrders() {
    var standingOrders = standingOrderService.getAllStandingOrders();
    return new ResponseEntity<>(standingOrders, HttpStatus.OK);
  }

  /**
   * Endpoint for retrieving a single standing order by its ID. The ID is passed in the path of the
   * URL.
   *
   * @param id The ID of the standing order to retrieve.
   * @return ResponseEntity with the retrieved standing order and an HTTP status code.
   * @throws StandingOrderNotFoundException If the standing order with the given ID is not found.
   */

  @GetMapping("/{id}")
  public ResponseEntity<StandingOrderDtoDetails> getStandingOrderById(@PathVariable long id)
      throws StandingOrderNotFoundException {
    var standingOrderDtoDetails = standingOrderService.getStandingOrderById(id);
    return new ResponseEntity<>(standingOrderDtoDetails, HttpStatus.OK);
  }

  /**
   * Endpoint for finding standing orders by name and variable symbol. The name and variable symbol
   * are passed as query parameters.
   *
   * @param name           The name to search for.
   * @param variableSymbol The variable symbol to search for.
   * @return ResponseEntity with the list of matching standing orders and an HTTP status code.
   */

  @GetMapping("/by-name-and-variable-symbol")
  public ResponseEntity<List<StandingOrderDtoDetails>> findByNameAndVariableSymbol(
      @RequestParam String name,
      @RequestParam String variableSymbol
  ) {
    var standingOrders = standingOrderService.getByNameAndVariableSymbol(name, variableSymbol);
    return new ResponseEntity<>(standingOrders, HttpStatus.OK);
  }

  /**
   * Endpoint for finding standing orders by valid from date, where the date falls within a given
   * range. The start and end dates are passed as query parameters.
   *
   * @param startDate The start date of the range to search in.
   * @param endDate   The end date of the range to search in.
   * @return ResponseEntity with the list of matching standing orders and an HTTP status code.
   */

  @GetMapping("/by-valid-from-between")
  public ResponseEntity<List<StandingOrderDtoDetails>> findByValidFromBetween(
      @RequestParam LocalDate startDate,
      @RequestParam LocalDate endDate
  ) {
    var standingOrders = standingOrderService.getByValidFromBetween(startDate, endDate);
    return new ResponseEntity<>(standingOrders, HttpStatus.OK);
  }

  /**
   * Endpoint for finding standing orders by valid from date, where the date is greater than a given
   * date. The date is passed as a query parameter.
   *
   * @param date The date to search for.
   * @return ResponseEntity with the list of matching standing orders and an HTTP status code.
   */
  @GetMapping("/by-valid-from-greater-than")
  public ResponseEntity<List<StandingOrderDtoDetails>> findByValidFromGreaterThan(
      @RequestParam LocalDate date
  ) {
    var standingOrders = standingOrderService.findByValidFromGreaterThanEqualOrderByValidFromAsc(
        date);
    return new ResponseEntity<>(standingOrders, HttpStatus.OK);
  }

  /**
   * Endpoint for creating a new    standing order. The standing order data is passed in the request
   * body as a {@link StandingOrderToCreateDto} object. The created standing order is returned as a
   * {@link StandingOrderDtoDetails} object.
   *
   * @param standingOrderToCreateDto The standing order data to create a new standing order.
   * @return ResponseEntity with the created standing order and an HTTP status code.
   */
  @PostMapping
  public ResponseEntity<StandingOrderDtoDetails> createStandingOrder(
      @RequestBody @Valid StandingOrderToCreateDto standingOrderToCreateDto
  ) {
    var createdStandingOrder = standingOrderService.createStandingOrder(standingOrderToCreateDto);
    return new ResponseEntity<>(createdStandingOrder, HttpStatus.CREATED);
  }

  /**
   * Endpoint for deleting a standing order by its ID. The ID is passed in the path of the URL.
   *
   * @param id The ID of the standing order to delete.
   * @throws StandingOrderNotFoundException If the standing order with the given ID is not found.
   */
  @PostMapping("/{id}")
  public void deleteStandingOrderById(@PathVariable long id) throws StandingOrderNotFoundException {
    standingOrderService.deleteStandingOrderById(id);
  }

  /**
   * Endpoint for updating a standing order by its ID. The ID is passed in the path of the URL, and
   * the updated standing order data is passed in the request body as a
   * {@link StandingOrderDtoDetails} object. The updated standing order is returned as a
   * {@link StandingOrderDtoDetails} object.
   *
   * @param id                    The ID of the standing order to update.
   * @param standingOrderToUpdate The updated standing order data.
   * @return ResponseEntity with the updated standing order and an HTTP status code.
   * @throws StandingOrderNotFoundException If the standing order with the given ID is not found.
   */
  @PutMapping("/{id}")
  public ResponseEntity<StandingOrderDtoDetails> updateStandingOrderById(
      @PathVariable long id,
      @RequestBody StandingOrderDtoDetails standingOrderToUpdate
  ) throws StandingOrderNotFoundException {
    if (id != standingOrderToUpdate.standingOrderId()) {
      log.warn("Wrong standing order id in the request body. id = {}", id);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Wrong standing order id in the request body.");
    }
    var standingOrderDtoDetails = standingOrderService
        .updateStandingOrder(standingOrderToUpdate);
    return new ResponseEntity<>(standingOrderDtoDetails, HttpStatus.OK);
  }
}
