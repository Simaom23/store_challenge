package com.simaom23.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simaom23.store.model.Price;
import com.simaom23.store.model.Response;
import com.simaom23.store.repository.PriceRepository;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTests {

        @InjectMocks
        PriceService priceService;

        @Mock
        PriceRepository priceRepository;

        private ArrayList<Price> mockArray;

        @BeforeEach
        public void setUp() {
                mockArray = new ArrayList<>();
        }

        // Parameterized test method
        @ParameterizedTest
        @MethodSource("testData")
        public void givenParameters_thenReturnCorrectPrices(Price[] entries, int productId, int brandId,
                        String date,
                        Response expectedResponse) {
                // Arrange
                for (Price entry : entries) {
                        mockArray.add(entry);
                }
                LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                when(priceRepository.findAllEntries(productId, brandId, Timestamp.valueOf(localDateTime)))
                                .thenReturn(mockArray);

                // Act
                Response out = priceService.checkPrice(productId, brandId, date).get();

                // Assert
                assertEquals(expectedResponse, out);
        }

        // Method providing test data
        static Stream<Object[]> testData() {
                return Stream.of(
                                new Object[] { // Test Case 1
                                                new Price[] { new Price(1, 1,
                                                                Timestamp.valueOf("2020-06-14 00:00:00"),
                                                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                                                35455, 0, new BigDecimal(35.50), "EUR") },
                                                35455, 1, "2020-06-14T10:00:00",
                                                new Response(35455, 1, 0, "2020-06-14 00:00:00.0",
                                                                "2020-12-31 23:59:59.0",
                                                                new BigDecimal(35.5), "EUR")
                                },
                                new Object[] { // Test Case 2
                                                new Price[] { new Price(2, 1,
                                                                Timestamp.valueOf("2020-06-14 15:00:00"),
                                                                Timestamp.valueOf("2020-06-14 18:30:00"), 2,
                                                                35455, 1, new BigDecimal(25.45), "EUR"),
                                                                new Price(1, 1, Timestamp
                                                                                .valueOf("2020-06-14 00:00:00"),
                                                                                Timestamp.valueOf(
                                                                                                "2020-12-31 23:59:59"),
                                                                                1,
                                                                                35455, 0, new BigDecimal(35.50),
                                                                                "EUR") },
                                                35455, 1, "2020-06-14T16:00:00",
                                                new Response(35455, 1, (float) 28.30986, "2020-06-14 15:00:00.0",
                                                                "2020-06-14 18:30:00.0",
                                                                new BigDecimal(25.45), "EUR")
                                },
                                new Object[] { // Test Case 3
                                                new Price[] { new Price(1, 1,
                                                                Timestamp.valueOf("2020-06-14 00:00:00"),
                                                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                                                35455, 0, new BigDecimal(35.50), "EUR") },
                                                35455, 1, "2020-06-14T21:00:00",
                                                new Response(35455, 1, 0, "2020-06-14 00:00:00.0",
                                                                "2020-12-31 23:59:59.0",
                                                                new BigDecimal(35.50), "EUR")
                                },
                                new Object[] { // Test Case 4
                                                new Price[] { new Price(3, 1,
                                                                Timestamp.valueOf("2020-06-15 00:00:00"),
                                                                Timestamp.valueOf("2020-06-15 11:00:00"), 3,
                                                                35455, 1, new BigDecimal(30.5), "EUR"),
                                                                new Price(1, 1, Timestamp
                                                                                .valueOf("2020-06-14 00:00:00"),
                                                                                Timestamp.valueOf(
                                                                                                "2020-12-31 23:59:59"),
                                                                                1,
                                                                                35455, 0, new BigDecimal(35.50),
                                                                                "EUR") },
                                                35455, 1, "2020-06-15T10:00:00",
                                                new Response(35455, 1, (float) 14.084503, "2020-06-15 00:00:00.0",
                                                                "2020-06-15 11:00:00.0",
                                                                new BigDecimal(30.5), "EUR")
                                },
                                new Object[] { // Test Case 5
                                                new Price[] { new Price(4, 1,
                                                                Timestamp.valueOf("2020-06-15 16:00:00"),
                                                                Timestamp.valueOf("2020-12-31 23:59:59"), 4,
                                                                35455, 0, new BigDecimal(38.95), "EUR"),
                                                                new Price(1, 1, Timestamp
                                                                                .valueOf("2020-06-14 00:00:00"),
                                                                                Timestamp.valueOf(
                                                                                                "2020-12-31 23:59:59"),
                                                                                1,
                                                                                35455, 0, new BigDecimal(35.50),
                                                                                "EUR") },
                                                35455, 1, "2020-06-16T21:00:00",
                                                new Response(35455, 1, (float) -9.7183075, "2020-06-15 16:00:00.0",
                                                                "2020-12-31 23:59:59.0",
                                                                new BigDecimal(38.95), "EUR")
                                });
        }
}
