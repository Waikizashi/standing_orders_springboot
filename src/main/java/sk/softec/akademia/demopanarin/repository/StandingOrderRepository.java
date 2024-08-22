package sk.softec.akademia.demopanarin.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sk.softec.akademia.demopanarin.model.StandingOrder;

/**
 * The StandingOrderRepository interface provides the methods to perform CRUD operations on the
 * database table "StandingOrders".
 */
@Repository
public interface StandingOrderRepository extends JpaRepository<StandingOrder, Long> {

  /**
   * Returns a list of standing orders that match the given name and variable symbol.
   *
   * @param name           the name of the standing order to search for
   * @param variableSymbol the variable symbol of the standing order to search for
   * @return a list of matching standing orders
   */
  List<StandingOrder> findByNameAndVariableSymbol(String name, String variableSymbol);

  /**
   * Returns a list of standing orders that have a validFrom date between the given start and end
   * dates.
   *
   * @param startDate the start date of the range to search for
   * @param endDate   the end date of the range to search for
   * @return a list of matching standing orders
   */
  List<StandingOrder> findByValidFromBetween(LocalDate startDate, LocalDate endDate);

  /**
   * Returns a list of standing orders that have a validFrom date greater than or equal to the given
   * date, ordered by validFrom in ascending order.
   *
   * @param date the date to search for
   * @return a list of matching standing orders
   */
  List<StandingOrder> findByValidFromGreaterThanEqualOrderByValidFromAsc(LocalDate date);
}