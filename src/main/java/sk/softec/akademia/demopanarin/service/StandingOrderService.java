package sk.softec.akademia.demopanarin.service;

import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;
import java.time.LocalDate;
import java.util.List;

import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;

/**
 * Service layer interface for managing standing orders.
 */
public interface StandingOrderService {

  /**
   * Creates a new standing order based on the given data.
   *
   * @param dto the data for the new standing order
   * @return the details of the created {@link StandingOrderToCreateDto}
   */
  StandingOrderDtoDetails createStandingOrder(StandingOrderToCreateDto dto);

  /**
   * Retrieves the details of the standing order with the given ID.
   *
   * @param standingOrderId the ID of the standing order to retrieve
   * @return the details of the retrieved {@link StandingOrderDtoDetails}
   * @throws StandingOrderNotFoundException if no standing order with the given ID was found
   */
  StandingOrderDtoDetails getStandingOrderById(long standingOrderId)
      throws StandingOrderNotFoundException;

  /**
   * Updates the details of the standing order with the given ID.
   *
   * @param dto the updated details for the standing order
   * @return the details of the updated {@link StandingOrderDtoDetails}
   * @throws StandingOrderNotFoundException if no standing order with the given ID was found
   */
  StandingOrderDtoDetails updateStandingOrder(StandingOrderDtoDetails dto)
      throws StandingOrderNotFoundException;

  /**
   * Deletes the standing order with the given ID.
   *
   * @param standingOrderId the ID of the standing order to delete
   * @throws StandingOrderNotFoundException if no standing order with the given ID was found
   */
  void deleteStandingOrderById(long standingOrderId) throws StandingOrderNotFoundException;

  /**
   * Retrieves a list of all standing orders.
   *
   * @return the list of all {@link StandingOrderDtoDetails}
   */
  List<StandingOrderDtoDetails> getAllStandingOrders();

  /**
   * Retrieves a list of standing orders with the given name and variable symbol.
   *
   * @param name           the name of the standing orders to retrieve
   * @param variableSymbol the variable symbol of the standing orders to retrieve
   * @return the list of matching {@link StandingOrderDtoDetails}
   */
  List<StandingOrderDtoDetails> getByNameAndVariableSymbol(String name, String variableSymbol);

  /**
   * Retrieves a list of standing orders with valid from dates between the given start and end
   * dates.
   *
   * @param startDate the start date for the valid from dates to retrieve
   * @param endDate   the end date for the valid from dates to retrieve
   * @return the list of matching {@link StandingOrderDtoDetails}
   */
  List<StandingOrderDtoDetails> getByValidFromBetween(LocalDate startDate, LocalDate endDate);

  /**
   * Retrieves a list of standing orders with valid from dates greater than or equal to the given
   * date.
   *
   * @param date the date for the valid from dates to retrieve
   * @return the list of matching {@link StandingOrderDtoDetails}
   */
  List<StandingOrderDtoDetails> findByValidFromGreaterThanEqualOrderByValidFromAsc(LocalDate date);
}