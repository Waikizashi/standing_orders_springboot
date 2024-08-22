package sk.softec.akademia.demopanarin.service.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.service.mapper.StandingOrderMapper;

/**
 * Implementation of the {@link StandingOrderMapper} interface, which maps between
 * {@link StandingOrder} and its DTOs.
 */
@Slf4j
@Component
public class StandingOrderMapperImpl implements StandingOrderMapper {

  /**
   * Implementation of {@link StandingOrderMapper#toStandingOrder(StandingOrderToCreateDto)}.
   */
  @Override
  public StandingOrder toStandingOrder(StandingOrderToCreateDto dto) {
    log.debug("Mapping (StandingOrderToCreateDto) -> (StandingOrder)  :{}", dto);
    var standingOrder = StandingOrder.builder()
        .accountId(dto.accountId())
        .accountNumber(dto.accountNumber())
        //.amount(dto.amount())
        .constantSymbol(dto.constantSymbol())
        .interval(dto.interval())
        .intervalSpecification(dto.intervalSpecification())
        .name(dto.name())
        .note(dto.note())
        .specificSymbol(dto.specificSymbol())
        .validFrom(dto.validFrom())
        .variableSymbol(dto.variableSymbol())
        .build();

    log.debug("Successfully mapped (StandingOrder)  :{} -> {}", dto, standingOrder);
    return standingOrder;
  }

  /**
   * Implementation of {@link StandingOrderMapper#toStandingOrderDetails(StandingOrder)}.
   */
  @Override
  public StandingOrderDtoDetails toStandingOrderDetails(StandingOrder standingOrder) {
    log.debug("Mapping (StandingOrder) -> (StandingOrderDtoDetails)  :{}", standingOrder);
    var standingOrderDtoDetails = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder.getStandingOrderId())
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        //.amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .interval(standingOrder.getInterval())
        .intervalSpecification(standingOrder.getIntervalSpecification())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    log.debug("Successfully mapped (StandingOrderDtoDetails)  :{} -> {}", standingOrder,
        standingOrderDtoDetails);
    return standingOrderDtoDetails;
  }

  /**
   * Implementation of {@link StandingOrderMapper#toStandingOrderToCreateDto(StandingOrder)}.
   */
  @Override
  public StandingOrderToCreateDto toStandingOrderToCreateDto(StandingOrder standingOrder) {
    log.debug("Mapping (StandingOrder) -> (StandingOrderToCreateDto)  :{}", standingOrder);
    var standingOrderToCreateDto = StandingOrderToCreateDto.builder()
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        //.amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .interval(standingOrder.getInterval())
        .intervalSpecification(standingOrder.getIntervalSpecification())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    log.debug("Successfully mapped (StandingOrderToCreateDto)  :{} -> {}", standingOrder,
        standingOrderToCreateDto);
    return standingOrderToCreateDto;
  }

  /**
   * Implementation of {@link StandingOrderMapper#toStandingOrder(StandingOrderDtoDetails)}.
   */
  @Override
  public StandingOrder toStandingOrder(StandingOrderDtoDetails dto) {
    log.debug("Mapping (StandingOrderDtoDetails) -> (StandingOrder)  :{}", dto);
    var standingOrder = StandingOrder.builder()
        .standingOrderId(dto.standingOrderId())
        .accountId(dto.accountId())
        .accountNumber(dto.accountNumber())
        //.amount(dto.amount())
        .constantSymbol(dto.constantSymbol())
        .interval(dto.interval())
        .intervalSpecification(dto.intervalSpecification())
        .name(dto.name())
        .note(dto.note())
        .specificSymbol(dto.specificSymbol())
        .validFrom(dto.validFrom())
        .variableSymbol(dto.variableSymbol())
        .build();

    log.debug("Successfully mapped (StandingOrder)  :{} -> {}", dto, standingOrder);
    return standingOrder;
  }
}
