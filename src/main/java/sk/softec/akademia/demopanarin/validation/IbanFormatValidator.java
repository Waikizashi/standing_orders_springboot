package sk.softec.akademia.demopanarin.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nl.garvelink.iban.IBAN;
import nl.garvelink.iban.IBANException;
import sk.softec.akademia.demopanarin.annotation.IbanFormatValid;

/**
 * A validator that checks if a given string represents a valid International Bank Account Number
 * (IBAN). This validator is intended to be used with the custom annotation
 * {@link IbanFormatValid}.
 */
public class IbanFormatValidator implements ConstraintValidator<IbanFormatValid, String> {

  /**
   * Initializes the validator.
   *
   * @param constraintAnnotation the {@link IbanFormatValid} annotation instance
   */
  @Override
  public void initialize(IbanFormatValid constraintAnnotation) {
  }

  /**
   * Validates whether the given string represents a valid IBAN format.
   *
   * @param value   the string to be validated
   * @param context the validator context
   * @return {@code true} if the given string represents a valid IBAN format, {@code false}
   * otherwise
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      IBAN.parse(value);
      return true;
    } catch (IBANException e) {
      return false;
    }
  }
}