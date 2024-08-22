package sk.softec.akademia.demopanarin.exception;

import lombok.Builder;

/**
 * A record class representing a custom error message with its details.
 *
 * @param errorMessage The error message describing the error's nature.
 * @param errorDetails The details of the error, providing additional context and information.
 */
@Builder
public record CustomErrorType(
    String errorMessage,
    String errorDetails
) {

}