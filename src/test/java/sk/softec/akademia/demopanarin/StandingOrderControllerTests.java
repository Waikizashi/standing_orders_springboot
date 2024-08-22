package sk.softec.akademia.demopanarin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import sk.softec.akademia.demopanarin.controller.StandingOrderController;
import sk.softec.akademia.demopanarin.dto.StandingOrderDtoDetails;
import sk.softec.akademia.demopanarin.dto.StandingOrderToCreateDto;
import sk.softec.akademia.demopanarin.model.StandingOrder;
import sk.softec.akademia.demopanarin.service.impl.StandingOrderServiceImpl;

/**
 * Unit tests for {@link StandingOrderController}.
 */
@WebMvcTest(StandingOrderController.class)
public class StandingOrderControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StandingOrderServiceImpl standingOrderServiceImpl;

  /**
   * Unit test for {@link StandingOrderController#getAllStandingOrders()}.
   */
  @Test
  void testGetAllStandingOrders() throws Exception {

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

    when(standingOrderServiceImpl.getAllStandingOrders()).thenReturn(
        expectedResponseListOfStandingOrdersDto);

    var expectedResult = """
        [
            {
                "accountId": 123,
                "accountNumber": "ABC123",
                "amount": 100,
                "constantSymbol": "CS111",
                "interval": 1,
                "intervalSpecification": 2,
                "name": "Test name_1",
                "note": "Test note_1",
                "specificSymbol": "SV111",
                "validFrom": "2023-01-01",
                "variableSymbol": "VV111"
            },
            {
                "accountId": 456,
                "accountNumber": "DEF456",
                "amount": 200,
                "constantSymbol": "CS222",
                "interval": 3,
                "intervalSpecification": 4,
                "name": "Test name_2",
                "note": "Test note_2",
                "specificSymbol": "SV222",
                "validFrom": "2023-01-02",
                "variableSymbol": "VV222"
            }
        ]""";

    mockMvc.perform(get("/standing-orders"))
        .andExpect(content().json(expectedResult));

  }

  /**
   * Unit test for {@link StandingOrderController#getStandingOrderById(long)}.
   */
  @Test
  void testGetStandingOrderById() throws Exception {
    var standingOrder = StandingOrder.builder()
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

    var standingOrderDto = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder.getStandingOrderId())
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        .amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .interval(standingOrder.getInterval())
        .intervalSpecification(standingOrder.getIntervalSpecification())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    when(standingOrderServiceImpl.getStandingOrderById(standingOrder.getStandingOrderId()))
        .thenReturn(standingOrderDto);

    var expectedResult = """
        {
            "accountId": 123,
            "accountNumber": "ABC123",
            "amount": 100,
            "constantSymbol": "CS111",
            "interval": 1,
            "intervalSpecification": 2,
            "name": "Test name_1",
            "note": "Test note_1",
            "specificSymbol": "SV111",
            "validFrom": "2023-01-01",
            "variableSymbol": "VV111"
          }""";

    mockMvc.perform(get("/standing-orders/{id}", standingOrder.getStandingOrderId()))
        .andExpect(content().json(expectedResult));
  }

  /**
   * Unit test for {@link StandingOrderController#findByNameAndVariableSymbol(String, String)}.
   */
  @Test
  void findByNameAndVariableSymbol() throws Exception {
    var name = "Test name_99";
    var vs = "VV999";

    var standingOrder1 = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name_99")
        .note("Test note_1")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV999")
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

    var standingOrder2 = StandingOrder.builder()
        .accountId(456)
        .accountNumber("DEF456")
        .amount(200)
        .constantSymbol("CS222")
        .interval(3)
        .intervalSpecification(4)
        .name("Test name_99")
        .note("Test note_2")
        .specificSymbol("SV222")
        .validFrom(LocalDate.parse("2023-01-02"))
        .variableSymbol("VV999")
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

    var expectedResult = """
        [
            {
              "accountId": 123,
              "accountNumber": "ABC123",
              "amount": 100,
              "constantSymbol": "CS111",
              "interval": 1,
              "intervalSpecification": 2,
              "name": "Test name_99",
              "note": "Test note_1",
              "specificSymbol": "SV111",
              "validFrom": "2023-01-01",
              "variableSymbol": "VV999"
            },
            {
              "accountId": 456,
              "accountNumber": "DEF456",
              "amount": 200,
              "constantSymbol": "CS222",
              "interval": 3,
              "intervalSpecification": 4,
              "name": "Test name_99",
              "note": "Test note_2",
              "specificSymbol": "SV222",
              "validFrom": "2023-01-02",
              "variableSymbol": "VV999"
            }
        ]""";

    when(standingOrderServiceImpl.getByNameAndVariableSymbol(name, vs))
        .thenReturn(expectedResponseListOfStandingOrdersDto);

    mockMvc.perform(get("/standing-orders/by-name-and-variable-symbol")
            .param("name", "Test name_99")
            .param("variableSymbol", "VV999"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResult));

  }

  /**
   * Unit test for {@link StandingOrderController#findByValidFromBetween(LocalDate, LocalDate)}.
   */
  @Test
  void testFindByValidFromBetween() throws Exception {
    var date1 = LocalDate.parse("2023-01-15");
    var date2 = LocalDate.parse("2023-01-27");

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
        .validFrom(date1)
        .variableSymbol("VV111")
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
        .validFrom(date2)
        .variableSymbol("VV222")
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

    var expectedResult = """
        [
            {
            "accountId": 123,
            "accountNumber": "ABC123",
            "amount": 100,
            "constantSymbol": "CS111",
            "interval": 1,
            "intervalSpecification": 2,
            "name": "Test name_1",
            "note": "Test note_1",
            "specificSymbol": "SV111",
            "validFrom": "2023-01-15",
            "variableSymbol": "VV111"
            },
            
            {
            "accountId": 456,
            "accountNumber": "DEF456",
            "amount": 200,
            "constantSymbol": "CS222",
            "interval": 3,
            "intervalSpecification": 4,
            "name": "Test name_2",
            "note": "Test note_2",
            "specificSymbol": "SV222",
            "validFrom": "2023-01-27",
            "variableSymbol": "VV222"
            }
        ]""";

    when(standingOrderServiceImpl.getByValidFromBetween(any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(expectedResponseListOfStandingOrdersDto);

    mockMvc.perform(get("/standing-orders/by-valid-from-between")
            .param("startDate", "2023-01-01")
            .param("endDate", "2023-01-31"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResult));

  }

  /**
   * Unit test for {@link StandingOrderController#findByValidFromGreaterThan(LocalDate)}.
   */
  @Test
  void findByValidFromGreaterThan() throws Exception {
    var date1 = LocalDate.parse("2023-01-15");
    var date2 = LocalDate.parse("2023-01-27");

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
        .validFrom(date1)
        .variableSymbol("VV111")
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
        .validFrom(date2)
        .variableSymbol("VV222")
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

    var expectedResult = """
        [
            {
            "accountId": 123,
            "accountNumber": "ABC123",
            "amount": 100,
            "constantSymbol": "CS111",
            "interval": 1,
            "intervalSpecification": 2,
            "name": "Test name_1",
            "note": "Test note_1",
            "specificSymbol": "SV111",
            "validFrom": "2023-01-15",
            "variableSymbol": "VV111"
            },
            
            {
            "accountId": 456,
            "accountNumber": "DEF456",
            "amount": 200,
            "constantSymbol": "CS222",
            "interval": 3,
            "intervalSpecification": 4,
            "name": "Test name_2",
            "note": "Test note_2",
            "specificSymbol": "SV222",
            "validFrom": "2023-01-27",
            "variableSymbol": "VV222"
            }
        ]""";

    when(standingOrderServiceImpl.findByValidFromGreaterThanEqualOrderByValidFromAsc(
        any(LocalDate.class)))
        .thenReturn(expectedResponseListOfStandingOrdersDto);

    mockMvc.perform(get("/standing-orders/by-valid-from-greater-than")
            .param("date", "2023-01-06"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResult));

  }

  /**
   * Unit test for {@link StandingOrderController#createStandingOrder(StandingOrderToCreateDto)}
   */
  @Test
  void testCreateStandingOrder() throws Exception {
    var standingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("SK3112000000198742637541")
        .amount(100)
        .constantSymbol("CS111")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name")
        .note("Test note")
        .specificSymbol("SV111")
        .validFrom(LocalDate.parse("2023-04-01"))
        .variableSymbol("VV111")
        .build();

    var standingOrderDtoDetails = StandingOrderDtoDetails.builder()
        .standingOrderId(standingOrder.getStandingOrderId())
        .accountId(standingOrder.getAccountId())
        .accountNumber(standingOrder.getAccountNumber())
        .amount(standingOrder.getAmount())
        .constantSymbol(standingOrder.getConstantSymbol())
        .interval(standingOrder.getInterval())
        .intervalSpecification(standingOrder.getIntervalSpecification())
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
        .interval(standingOrder.getInterval())
        .intervalSpecification(standingOrder.getIntervalSpecification())
        .name(standingOrder.getName())
        .note(standingOrder.getNote())
        .specificSymbol(standingOrder.getSpecificSymbol())
        .validFrom(standingOrder.getValidFrom())
        .variableSymbol(standingOrder.getVariableSymbol())
        .build();

    String standingOrderToCreateDtoJson = """
            {
              "accountId": 123,
              "accountNumber": "SK3112000000198742637541",
              "amount": 100,
              "constantSymbol": "CS111",
              "interval": 1,
              "intervalSpecification": 2,
              "name": "Test name",
              "note": "Test note",
              "specificSymbol": "SV111",
              "validFrom": "2023-04-01",
              "variableSymbol": "VV111"
            }
        """;

    when(standingOrderServiceImpl.createStandingOrder(standingOrderToCreateDto))
        .thenReturn(standingOrderDtoDetails);

    var expectedResult = """
            {
              "standingOrderId": 0,
              "accountId": 123,
              "accountNumber": "SK3112000000198742637541",
              "amount": 100,
              "constantSymbol": "CS111",
              "interval": 1,
              "intervalSpecification": 2,
              "name": "Test name",
              "note": "Test note",
              "specificSymbol": "SV111",
              "validFrom": "2023-04-01",
              "variableSymbol": "VV111"
            }
        """;

    mockMvc.perform(post("/standing-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(standingOrderToCreateDtoJson))
        .andExpect(status().isCreated())
        .andExpect(content().json(expectedResult));

  }

  /**
   * Unit test for {@link StandingOrderController#deleteStandingOrderById(long)}.
   */
  @Test
  void testDeleteStandingOrderById() throws Exception {
    long standingOrderId = 1L;
    mockMvc.perform(post("/standing-orders/{id}", standingOrderId))
        .andExpect(status().isOk());
    verify(standingOrderServiceImpl, times(1)).deleteStandingOrderById(standingOrderId);
  }

  /**
   * Unit test for
   * {@link StandingOrderController#updateStandingOrderById(long, StandingOrderDtoDetails)}
   */
  @Test
  void testUpdateStandingOrderById() throws Exception {
    var currentStandingOrder = StandingOrder.builder()
        .accountId(123)
        .accountNumber("ABC123")
        .amount(100)
        .constantSymbol("CS")
        .interval(1)
        .intervalSpecification(2)
        .name("Test name")
        .note("Test note")
        .specificSymbol("SV")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("VV")
        .build();

    var newStandingOrderDto = StandingOrderDtoDetails.builder()
        .standingOrderId(currentStandingOrder.getStandingOrderId())
        .accountId(1234)
        .accountNumber("ABCD1234")
        .amount(1000)
        .constantSymbol("newCS")
        .interval(11)
        .intervalSpecification(22)
        .name("NEW Test name")
        .note("NEW Test note")
        .specificSymbol("newSV")
        .validFrom(LocalDate.parse("2023-01-01"))
        .variableSymbol("newVV")
        .build();

    when(standingOrderServiceImpl.updateStandingOrder(newStandingOrderDto))
        .thenReturn(newStandingOrderDto);

    var expectedResult = """
        {
          "accountId":1234,
          "accountNumber":"ABCD1234",
          "amount":1000,
          "constantSymbol":"newCS",
          "interval":11,
          "intervalSpecification":22,
          "name":"NEW Test name",
          "note":"NEW Test note",
          "specificSymbol":"newSV",
          "validFrom":"2023-01-01",
          "variableSymbol":"newVV"
        }""";

    mockMvc.perform(put("/standing-orders/{id}",
            currentStandingOrder.getStandingOrderId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(expectedResult))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResult));

  }
}
