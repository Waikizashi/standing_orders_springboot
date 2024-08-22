package sk.softec.akademia.demopanarin.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import sk.softec.akademia.demopanarin.annotation.IbanFormatValid;

/**
 * Data transfer object for creating a new standing order. It contains the following properties:
 *
 * @param accountId             The ID of the account the standing order is associated with.
 * @param accountNumber         The number of the account the standing order is associated with.
 *                              Must be a valid IBAN format.
 * @param amount                The amount of money to be transferred with the standing order.
 * @param constantSymbol        The constant symbol of the standing order. Must have a maximum
 *                              length of 10 characters.
 * @param interval              The interval of the standing order.
 * @param intervalSpecification The specification of the interval of the standing order.
 * @param name                  The name of the standing order. Must not be blank and have a maximum
 *                              length of 512 characters.
 * @param note                  A note associated with the standing order. Must have a maximum
 *                              length of 255 characters.
 * @param specificSymbol        The specific symbol of the standing order. Must have a maximum
 *                              length of 10 characters.
 * @param validFrom             The date the standing order becomes valid. Must be a future or
 *                              present date.
 * @param variableSymbol        The variable symbol of the standing order. Must have a maximum
 *                              length of 10 characters. This class is used for transferring data
 *                              between the controller and the service layer when creating a new
 *                              standing order.
 */
@Builder
public record StandingOrderToCreateDto(

    @Min(0)
    int accountId,

    @IbanFormatValid
    String accountNumber,

    @Min(1)
    int amount,

    @Size(max = 10)
    String constantSymbol,

    @Min(1)
    int interval,

    @Min(1)
    int intervalSpecification,

    @NotBlank
    @Size(max = 512)
    String name,

    @Size(max = 255)
    String note,

    @Size(max = 10)
    String specificSymbol,

    @FutureOrPresent
    LocalDate validFrom,

    @Size(max = 10)
    String variableSymbol) {

}