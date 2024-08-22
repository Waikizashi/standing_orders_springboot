package sk.softec.akademia.demopanarin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.repository.StandingOrderRepository;

/**
 * This class contains integration tests for verifying the mapping of entities.
 */
@SpringBootTest
class EntitiesMappingTests {

  /**
   * The repository for performing CRUD operations on the {@link StandingOrder} entity.
   */
  @Autowired
  private StandingOrderRepository standingOrderRepository;

  /**
   * Tests whether the mapping of the {@link StandingOrder} entity works correctly.
   */
  @Test
  void StandingOrderMapping() {
    List<StandingOrder> standingOrders = standingOrderRepository.findAll();
    assertThat(standingOrders).isNotEmpty();
  }
}
