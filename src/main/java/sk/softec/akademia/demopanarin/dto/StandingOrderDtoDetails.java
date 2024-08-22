package sk.softec.akademia.demopanarin.dto;

import java.time.LocalDate;
import lombok.Builder;

/**
 * Data transfer object for representing the details of a standing order. It contains the following
 * properties:
 *
 * @param standingOrderId       The ID of the standing order.
 * @param accountId             The ID of the account the standing order is associated with.
 * @param accountNumber         The number of the account the standing order is associated with.
 * @param amount                The amount of money to be transferred with the standing order.
 * @param constantSymbol        The constant symbol of the standing order.
 * @param interval              The interval of the standing order.
 * @param intervalSpecification The specification of the interval of the standing order.
 * @param name                  The name of the standing order.
 * @param note                  A note associated with the standing order.
 * @param specificSymbol        The specific symbol of the standing order.
 * @param validFrom             The date the standing order becomes valid.
 * @param variableSymbol        The variable symbol of the standing order. This class is used for
 *                              transferring data between the controller and the service layer.
 */

@Builder
public record StandingOrderDtoDetails(
    long standingOrderId,
    int accountId,
    String accountNumber,
    int amount,
    String constantSymbol,
    int interval,
    int intervalSpecification,
    String name,
    String note,
    String specificSymbol,
    LocalDate validFrom,
    String variableSymbol) {

}