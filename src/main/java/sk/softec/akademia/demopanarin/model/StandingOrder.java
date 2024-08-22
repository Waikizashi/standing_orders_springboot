package sk.softec.akademia.demopanarin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
// import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
// import lombok.experimental.Accessors;

/**
 * A JPA entity class representing a standing order. This class is used to map a standing order to a
 * database table and vice versa.
 */
@Entity
@Table(name = "StandingOrders")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StandingOrder {
  /**
   * The standingOrderId associated with the standing order. Automatically generated value.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long standingOrderId;
  /**
   * The accountId associated with the standing order.
   */
  private int accountId;

  /**
   * The account number associated with the standing order. Must not be null.
   */
  @NonNull
  private String accountNumber;
  /**
   * The amount associated with the standing order.
   */
  //private int amount;

  /**
   * The constant symbol associated with the standing order. Must not be null.
   */
  @NonNull
  private String constantSymbol;
  /**
   * The interval associated with the standing order.
   */
  private int interval;
  /**
   * The intervalSpecification associated with the standing order.
   */
  private int intervalSpecification;

  /**
   * The name associated with the standing order. Must not be null.
   */
  @NonNull
  private String name;

  /**
   * The note associated with the standing order. Must not be null.
   */
  @NonNull
  private String note;

  /**
   * The specific symbol associated with the standing order. Must not be null.
   */
  @NonNull
  private String specificSymbol;

  /**
   * The date from which the standing order is valid. Must not be null.
   */
  @NonNull
  private LocalDate validFrom;

  /**
   * The variable symbol associated with the standing order. Must not be null.
   */
  @NonNull
  private String variableSymbol;

}