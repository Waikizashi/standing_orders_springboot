package sk.softec.akademia.demopanarin;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.repository.StandingOrderRepository;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Integration tests for custom methods implementation of {@link StandingOrderRepository}.
 */
@DataJpaTest
public class CustomRepositoryMethodsImplementationTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StandingOrderRepository standingOrderRepository;

  /**
   * Unit test for {@link StandingOrderRepository#findByNameAndVariableSymbol(String, String)}.
   */
  @Test
  public void testFindByNameAndVariableSymbol() {
    StandingOrder standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("SK3112000000198742637541")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name_1")
        .note("Test note_1")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("VV111")
        .build();

    entityManager.persist(standingOrder);
    entityManager.flush();

    List<StandingOrder> expectedResult = standingOrderRepository.findByNameAndVariableSymbol(
        "Test name_1", "VV111");

    assertThat(expectedResult).contains(standingOrder);
  }

  @Test
  void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
  }

  /**
   * Unit test for
   * {@link StandingOrderRepository#findByValidFromGreaterThanEqualOrderByValidFromAsc(LocalDate)}.
   */
  @Test
  void testFindingByValidFromGreaterThanEqualOrderByValidFromAsc() {
    var standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("SK3112000000198742637541")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name_1")
        .note("Test note_1")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("VV111")
        .build();

    entityManager.persist(standingOrder);
    entityManager.flush();

    var expectedResult = standingOrderRepository.findByValidFromGreaterThanEqualOrderByValidFromAsc(
        LocalDate.parse("2023-01-01"));

    assertThat(expectedResult).contains(standingOrder);
  }

  /**
   * Unit test for {@link StandingOrderRepository#findByValidFromBetween(LocalDate, LocalDate)}.
   */
  @Test
  void testFindingByValidFromBetween() {
    var standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("SK3112000000198742637541")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name_1")
        .note("Test note_1")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("VV111")
        .build();

    entityManager.persist(standingOrder);
    entityManager.flush();

    var expectedResult = standingOrderRepository.findByValidFromBetween(
        LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-03"));

    assertThat(expectedResult).contains(standingOrder);
  }

}
