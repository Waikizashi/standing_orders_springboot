package sk.softec.akademia.demopanarin.service.mapper;

import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.model.StandingOrder;

/**
 * Mapper interface for mapping between StandingOrder and its DTOs: {@link StandingOrderDtoDetails}
 * DTO and {@link StandingOrderToCreateDto} DTO.
 */
public interface StandingOrderMapper {

  /**
   * Maps a {@link StandingOrderToCreateDto} object to a {@link StandingOrder} object.
   *
   * @param dto the {@link StandingOrderToCreateDto} object to be mapped
   * @return a {@link StandingOrder} object
   */
  StandingOrder toStandingOrder(StandingOrderToCreateDto dto);

  /**
   * Maps a {@link StandingOrderDtoDetails} object to a {@link StandingOrder} object.
   *
   * @param dto the {@link StandingOrderDtoDetails} object to be mapped
   * @return a {@link StandingOrder} object
   */
  StandingOrder toStandingOrder(StandingOrderDtoDetails dto);

  /**
   * Maps a {@link StandingOrder} object to a {@link StandingOrderDtoDetails} object.
   *
   * @param standingOrder the {@link StandingOrder} object to be mapped
   * @return a {@link StandingOrderDtoDetails} object
   */
  StandingOrderDtoDetails toStandingOrderDetails(StandingOrder standingOrder);

  /**
   * Maps a {@link StandingOrder} object to a {@link StandingOrderToCreateDto} object.
   *
   * @param standingOrder the {@link StandingOrder} object to be mapped
   * @return a {@link StandingOrderToCreateDto} object
   */
  StandingOrderToCreateDto toStandingOrderToCreateDto(StandingOrder standingOrder);
}