package sk.softec.akademia.demopanarin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import sk.softec.akademia.demopanarin.exception.StandingOrderNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.repository.StandingOrderRepository;
import sk.softec.akademia.demopanarin.service.impl.StandingOrderServiceImpl;
import sk.softec.akademia.demopanarin.service.mapper.impl.StandingOrderMapperImpl;

/**
 * This is a unit test class for the StandingOrderServiceImpl class.
 * <p>
 * It uses Mockito for mocking dependencies and JUnit 5 for assertions and annotations.
 */
@ExtendWith(MockitoExtension.class)
public class StandingOrderServiceImplTests {

  @Mock
  private StandingOrderRepository standingOrderRepository;

  @Mock
  private StandingOrderMapperImpl standingOrderMapperImpl;

  @InjectMocks
  private StandingOrderServiceImpl standingOrderServiceImpl;

  /**
   * Test class for the
   * {@link StandingOrderServiceImpl#createStandingOrder(StandingOrderToCreateDto)} method.
   */
  @Test
  void testCreateStandingOrder() {

    var standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS")
        .name("Test name")
        .note("Test note")
        .specificSymbol("SV")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV")
        .build();

    var standingOrderDtoDetails = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder.getStandingOrderId())
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        .amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();
    var standingOrderToCreateDto = StandingOrderToCreateDto.builder()
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        .amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    when(standingOrderMapperImpl.toStandingOrder(standingOrderToCreateDto))
        .thenReturn(standingOrder);
    when(standingOrderRepository.save(standingOrder))
        .thenReturn(standingOrder);
    when(standingOrderMapperImpl.toStandingOrderDetails(standingOrder))
        .thenReturn(standingOrderDtoDetails);

    StandingOrderDtoDetails createdStandingOrder = standingOrderServiceImpl
        .createStandingOrder(standingOrderToCreateDto);

    assertThat(standingOrderDtoDetails).isEqualTo(createdStandingOrder);
  }

  /**
   * Test class for the {@link StandingOrderServiceImpl#getStandingOrderById(long)} method.
   */
  @Test
  void gettingStandingOrderById() throws StandingOrderNotFoundException {

    var standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS")
        .name("Test name")
        .note("Test note")
        .specificSymbol("SV")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV")
        .build();

    var expectedStandingOrder = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder.getStandingOrderId())
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        .amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    when(standingOrderRepository.findById(standingOrder.getStandingOrderId()))
        .thenReturn(Optional.of(standingOrder));

    when(standingOrderMapperImpl.toStandingOrderDetails(standingOrder))
        .thenReturn(expectedStandingOrder);

    StandingOrderDtoDetails actualStandingOrder = standingOrderServiceImpl
        .getStandingOrderById(standingOrder.getStandingOrderId());

    assertThat(actualStandingOrder).isEqualTo(expectedStandingOrder);
  }

  /**
   * Test class for the
   * {@link StandingOrderServiceImpl#updateStandingOrder(StandingOrderDtoDetails)} method.
   */
  @Test
  void updatingStandingOrder() throws StandingOrderNotFoundException {

    var currentStandingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS")
        .name("Test name")
        .note("Test note")
        .specificSymbol("SV")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV")
        .build();

    long standingOrderId = currentStandingOrder.getStandingOrderId();

    var newStandingOrderDto = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrderId)
        .accountId(1234)
        .accountNumber("ABCd1234")
        .amount(1000)
        .constantSymbol("newCS")
        .name("NEW Test name")
        .note("NEW Test note")
        .specificSymbol("newSV")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("newVV")
        .build();

    var newStandingOrder = StandingOrder.builder()
        .standingOrderId(standingOrderId)
        .accountId(newStandingOrderDto.accountId())
        .accountNumber(newStandingOrderDto.accountNumber())
        .amount(newStandingOrderDto.amount())
        .constantSymbol(newStandingOrderDto.constantSymbol())
        .name(newStandingOrderDto.name())
        .note(newStandingOrderDto.note())
        .specificSymbol(newStandingOrderDto.specificSymbol())
        .validFrom(newStandingOrderDto.validFrom())
        .variableSymbol(newStandingOrderDto.variableSymbol())
        .build();

    when(standingOrderRepository.findById(standingOrderId))
        .thenReturn(Optional.of(currentStandingOrder));

    when(standingOrderMapperImpl.toStandingOrder(newStandingOrderDto))
        .thenReturn(newStandingOrder);

    when(standingOrderRepository.save(any(StandingOrder.class)))
        .thenReturn(newStandingOrder);

    when(standingOrderMapperImpl.toStandingOrderDetails(newStandingOrder))
        .thenReturn(newStandingOrderDto);

    StandingOrderDtoDetails result = standingOrderServiceImpl
        .updateStandingOrder(newStandingOrderDto);

    verify(standingOrderRepository, times(1)).save(newStandingOrder);

    assertThat(result).isEqualTo(newStandingOrderDto);
  }

  /**
   * Test class for the {@link StandingOrderServiceImpl#deleteStandingOrderById(long)} method.
   */
  @Test
  void deletingStandingOrderById() throws StandingOrderNotFoundException {
    var standingOrderId = 1L;
    StandingOrder standingOrder = new StandingOrder();

    standingOrder.setStandingOrderId(standingOrderId);

    when(standingOrderRepository.findById(standingOrderId)).thenReturn(Optional.of(standingOrder));

    standingOrderServiceImpl.deleteStandingOrderById(standingOrderId);

    verify(standingOrderRepository, times(1)).deleteById(standingOrderId);
  }

  /**
   * Test class for the {@link StandingOrderServiceImpl#getAllStandingOrders()} method.
   */
  @Test
  void gettingAllStandingOrders() {
    var standingOrder1 = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name_1")
        .note("Test note_1")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV111")
        .build();

    var standingOrder2 = StandingOrder.builder()
        .accountId(456)
        .accountNumber("DEF456")
        .amount(200)
        .constantSymbol("CS222")
        .interval(3)
        .intervalSpecification(4)
        .name("Test name_2")
        .note("Test note_2")
        .specificSymbol("SV222")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("VV222")
        .build();

    var standingOrderDto1 = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder1.getStandingOrderId())
        .accountId(standingOrder1.getAccountId())
        .accountNumber(standingOrder1.getAccountNumber())
        .amount(standingOrder1.getAmount())
        .constantSymbol(standingOrder1.getConstantSymbol())
        .interval(standingOrder1.getInterval())
        .intervalSpecification(standingOrder1.getIntervalSpecification())
        .name(standingOrder1.getName())
        .note(standingOrder1.getNote())
        .specificSymbol(standingOrder1.getSpecificSymbol())
        .validFrom(standingOrder1.getValidFrom())
        .variableSymbol(standingOrder1.getVariableSymbol())
        .build();

    var standingOrderDto2 = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder2.getStandingOrderId())
        .accountId(standingOrder2.getAccountId())
        .accountNumber(standingOrder2.getAccountNumber())
        .amount(standingOrder2.getAmount())
        .constantSymbol(standingOrder2.getConstantSymbol())
        .interval(standingOrder2.getInterval())
        .intervalSpecification(standingOrder2.getIntervalSpecification())
        .name(standingOrder2.getName())
        .note(standingOrder2.getNote())
        .specificSymbol(standingOrder2.getSpecificSymbol())
        .validFrom(standingOrder2.getValidFrom())
        .variableSymbol(standingOrder2.getVariableSymbol())
        .build();

    var expectedResponseListOfStandingOrdersDto = List.of(standingOrderDto1, standingOrderDto2);
    var expectedResponseListOfStandingOrders = List.of(standingOrder1, standingOrder2);

    when(standingOrderRepository.findAll())
        .thenReturn(expectedResponseListOfStandingOrders);

    when(standingOrderMapperImpl.toStandingOrderDetails(standingOrder1))
        .thenReturn(standingOrderDto1);

    when(standingOrderMapperImpl.toStandingOrderDetails(standingOrder2))
        .thenReturn(standingOrderDto2);

    List<StandingOrderDtoDetails> requestResult = standingOrderServiceImpl.getAllStandingOrders();

    verify(standingOrderRepository, times(1)).findAll();

    assertThat(requestResult).isEqualTo(expectedResponseListOfStandingOrdersDto);

  }
}
