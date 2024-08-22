package sk.softec.akademia.demopanarin.service.impl;

import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.repository.StandingOrderRepository;
import sk.softec.akademia.demopanarin.service.StandingOrderService;
import sk.softec.akademia.demopanarin.service.mapper.impl.StandingOrderMapperImpl;

/**
 * Implementation of the {@link StandingOrderService} interface, providing CRUD operations for
 * {@link StandingOrder} entities.
 */
@Slf4j
@Service
@AllArgsConstructor
public class StandingOrderServiceImpl implements StandingOrderService {

  /**
   * An instance of the {@link StandingOrderRepository} that provides the methods to interact with
   * the database.
   */
  private final StandingOrderRepository standingOrderRepository;

  /**
   * An instance of the {@link StandingOrderMapperImpl} that provides the methods to map the
   * {@link StandingOrder} entity to {@link StandingOrderDtoDetails} DTO and
   * {@link StandingOrderToCreateDto} DTO and back.
   */
  private final StandingOrderMapperImpl standingOrderMapperImpl;

  /**
   * Implementation of {@link StandingOrderService#createStandingOrder(StandingOrderToCreateDto)}.
   */
  @Override
  public StandingOrderDtoDetails createStandingOrder(
      StandingOrderToCreateDto standingOrderToCreateDto
  ) {
    var newStandingOrderDtoDetails = standingOrderMapperImpl
        .toStandingOrderDetails(standingOrderRepository
            .save(standingOrderMapperImpl.toStandingOrder(standingOrderToCreateDto)));

    log.info(
        "Service CreateStandingOrder successfully created new standing order: {} ",
        newStandingOrderDtoDetails);
    return newStandingOrderDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderService#getStandingOrderById(long)}.
   */
  @Override
  public StandingOrderDtoDetails getStandingOrderById(long standingOrderId)
      throws StandingOrderNotFoundException {
    var standingOrderDtoDetails = standingOrderRepository
        .findById(standingOrderId)
        .map(standingOrderMapperImpl::toStandingOrderDetails)
        .orElseThrow(() -> {
          log.warn("Standing order with id {} not found", standingOrderId);
          return new StandingOrderNotFoundException(
              "Standing order with id: " +
                  standingOrderId +
                  " not found"
          );
        });

    log.info(
        "Service GetStandingOrderById successfully got standing order: {}",
        standingOrderDtoDetails);
    return standingOrderDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderService#updateStandingOrder(StandingOrderDtoDetails)}.
   */
  @Override
  public StandingOrderDtoDetails updateStandingOrder(
      StandingOrderDtoDetails standingOrderDtoDetails
  ) throws StandingOrderNotFoundException {
    standingOrderRepository
        .findById(standingOrderDtoDetails.standingOrderId())
        .orElseThrow(() -> {
          log.warn("Standing order with id {} not found",
              standingOrderDtoDetails.standingOrderId());
          return new StandingOrderNotFoundException(
              "Standing order with id: " +
                  standingOrderDtoDetails.standingOrderId() +
                  " not found"
          );
        });

    var updatedStandingOrderDtoDetails = standingOrderMapperImpl
        .toStandingOrderDetails(standingOrderRepository
            .save(standingOrderMapperImpl
                .toStandingOrder(standingOrderDtoDetails)));

    log.info(
        "Service UpdateStandingOrder successfully updated standing order: {}",
        updatedStandingOrderDtoDetails);
    return updatedStandingOrderDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderService#deleteStandingOrderById(long)}.
   */
  @Override
  public void deleteStandingOrderById(long standingOrderId) throws StandingOrderNotFoundException {
    standingOrderRepository
        .findById(standingOrderId)
        .orElseThrow(() -> {
          log.warn("Standing order with id {} not found", standingOrderId);
          return new StandingOrderNotFoundException(
              "Standing order with id: " +
                  standingOrderId +
                  " not found"
          );
        });

    standingOrderRepository.deleteById(standingOrderId);
    log.info(
        "Service DeleteStandingOrderById successfully deleted standing order with id: {}",
        standingOrderId);
  }

  /**
   * Implementation of {@link StandingOrderService#getAllStandingOrders()}.
   */
  @Override
  public List<StandingOrderDtoDetails> getAllStandingOrders() {
    var standingOrdersDtoDetails = standingOrderRepository
        .findAll()
        .stream()
        .map(standingOrderMapperImpl::toStandingOrderDetails)
        .toList();

    log.info(
        "Service GetAllStandingOrders successfully returned {} records",
        standingOrdersDtoDetails.size());
    return standingOrdersDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderService#getByNameAndVariableSymbol(String, String)}.
   */
  @Override
  public List<StandingOrderDtoDetails> getByNameAndVariableSymbol(
      String name,
      String variableSymbol
  ) {
    var standingOrdersDtoDetails = standingOrderRepository
        .findByNameAndVariableSymbol(name, variableSymbol)
        .stream()
        .map(standingOrderMapperImpl::toStandingOrderDetails)
        .toList();

    log.info(
        "Service FindByNameAndVariableSymbol successfully returned {} records",
        standingOrdersDtoDetails.size());
    return standingOrdersDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderService#getByValidFromBetween(LocalDate, LocalDate)}.
   */
  @Override
  public List<StandingOrderDtoDetails> getByValidFromBetween(
      LocalDate startDate,
      LocalDate endDate
  ) {
    var standingOrdersDtoDetails = standingOrderRepository
        .findByValidFromBetween(startDate, endDate)
        .stream()
        .map(standingOrderMapperImpl::toStandingOrderDetails)
        .toList();

    log.info(
        "Service FindByValidFromBetween successfully returned {} records",
        standingOrdersDtoDetails.size());
    return standingOrdersDtoDetails;
  }

  /**
   * Implementation of
   * {@link StandingOrderService#findByValidFromGreaterThanEqualOrderByValidFromAsc(LocalDate)}.
   */
  @Override
  public List<StandingOrderDtoDetails> findByValidFromGreaterThanEqualOrderByValidFromAsc(
      LocalDate date) {
    var standingOrdersDtoDetails = standingOrderRepository
        .findByValidFromGreaterThanEqualOrderByValidFromAsc(date)
        .stream()
        .map(standingOrderMapperImpl::toStandingOrderDetails)
        .toList();

    log.info(
        "Service findByValidFromGreaterThan successfully returned {} records",
        standingOrdersDtoDetails.size());
    return standingOrdersDtoDetails;
  }

}
