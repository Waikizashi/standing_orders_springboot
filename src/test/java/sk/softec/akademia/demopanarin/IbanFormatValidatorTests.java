package sk.softec.akademia.demopanarin;

import org.junit.jupiter.api.BeforeAll;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sk.softec.akademia.demopanarin.annotation.IbanFormatValid;

import java.util.Set;
import sk.softec.akademia.demopanarin.validation.IbanFormatValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains unit tests for the {@link IbanFormatValidator} class. It uses the JUnit 5
 * testing framework and the AssertJ library for fluent assertions.
 */
class IbanFormatValidatorTests {

  private static Validator validator;

  /**
   * Sets up the validator factory before all tests run.
   */
  @BeforeAll
  static void setUp() {
    try (var factory = Validation.buildDefaultValidatorFactory()) {
      validator = factory.getValidator();
    }
  }

  /**
   * Tests that the validator returns no violations for valid IBAN formats.
   */
  @ParameterizedTest
  @ValueSource(strings = {"SK3112000000198742637541", "SK31 1200 0000 1987 4263 7541"})
  void validIbanFormat(String input) {
    Set<ConstraintViolation<TestIban>> violations = validator.validateValue(TestIban.class, "iban",
        input);
    assertThat(violations).isEmpty();
  }

  /**
   * Tests that the validator returns a violation for invalid IBAN formats.
   */
  @ParameterizedTest
  @ValueSource(strings = {"SK31 1200 0000 1987 4263 75", "SKXX1200 0000 1987 4263 75"})
  void invalidIbanFormat(String input) {
    Set<ConstraintViolation<TestIban>> violations = validator.validateValue(TestIban.class, "iban",
        input);
    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid IBAN format");
  }

  /**
   * A helper class used for testing the {@link IbanFormatValidator} class.
   */
  static class TestIban {

    @IbanFormatValid
    private String iban;
  }
}
