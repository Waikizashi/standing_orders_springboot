package sk.softec.akademia.demopanarin.annotation;

import static java.lang.annotation.ElementType.FIELD;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import sk.softec.akademia.demopanarin.validation.IbanFormatValidator;

/**
 * This annotation is used to mark a field that should be validated as a valid IBAN format string.
 * It applies the {@link IbanFormatValidator} validator to the field. If the validation fails, the
 * default error message is "Invalid IBAN format".
 */
@Documented
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IbanFormatValidator.class)
public @interface IbanFormatValid {

  String message() default "Invalid IBAN format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}