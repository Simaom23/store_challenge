package com.simaom23.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simaom23.store.model.Price;
import com.simaom23.store.model.Response;
import com.simaom23.store.repository.PriceRepository;
import com.simaom23.store.service.PriceService;

/* 
* The DB results were more predictable so 
* i did not write tests to check the repository
* so made mockups to replicate the DB response,
* in a dev environment i'd have to check both services
*/
@ExtendWith(MockitoExtension.class)
public class PriceService_Testing {

        @InjectMocks
        PriceService priceService;

        @Mock
        PriceRepository priceRepository;

        @Test
        public void givenParameters_thenReturnCorrectProducts1() {
                // Arrange
                ArrayList<Price> mock_array = new ArrayList<>();
                Price entry = new Price(1, 1, Timestamp.valueOf("2020-06-14 00:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                35455, 0, new BigDecimal(35.50),
                                "EUR");
                mock_array.add(entry);

                lenient().when(priceRepository.findAllEntries(35455, 1, Timestamp.valueOf("2020-06-14 10:00:00")))
                                .thenReturn(mock_array);

                Response valid_out = new Response(35455, 1, 0, "2020-06-14 00:00:00.0",
                                "2020-12-31 23:59:59.0",
                                new BigDecimal(35.5), "EUR");

                // Act
                Response out = priceService.checkProduct(35455, 1, "2020-06-14 10:00:00");

                // Assert
                assertEquals(valid_out, out);
        }

        @Test
        public void givenParameters_thenReturnCorrectProducts2() {
                // Arrange
                ArrayList<Price> mock_array = new ArrayList<>();

                /* Setup DB entries returns */
                Price first_entry = new Price(2, 1, Timestamp.valueOf("2020-06-14 15:00:00"),
                                Timestamp.valueOf("2020-06-14 18:30:00"), 2,
                                35455, 1, new BigDecimal(25.45),
                                "EUR");
                Price second_entry = new Price(1, 1, Timestamp.valueOf("2020-06-14 00:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                35455, 0, new BigDecimal(35.50),
                                "EUR");
                mock_array.add(first_entry);
                mock_array.add(second_entry);

                lenient().when(priceRepository.findAllEntries(35455, 1, Timestamp.valueOf("2020-06-14 16:00:00")))
                                .thenReturn(mock_array);

                Response valid_out = new Response(35455, 1, (float) 28.30986, "2020-06-14 15:00:00.0",
                                "2020-06-14 18:30:00.0",
                                new BigDecimal(25.45), "EUR");

                // Act
                Response out = priceService.checkProduct(35455, 1, "2020-06-14 16:00:00");

                // Assert
                assertEquals(valid_out, out);
        }

        @Test
        public void givenParameters_thenReturnCorrectProducts3() {
                // Arrange
                ArrayList<Price> mock_array = new ArrayList<>();

                /* Setup DB entries returns */
                Price first_entry = new Price(1, 1, Timestamp.valueOf("2020-06-14 00:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                35455, 0, new BigDecimal(35.50),
                                "EUR");
                mock_array.add(first_entry);

                lenient().when(priceRepository.findAllEntries(35455, 1, Timestamp.valueOf("2020-06-14 21:00:00")))
                                .thenReturn(mock_array);

                Response valid_out = new Response(35455, 1, 0, "2020-06-14 00:00:00.0",
                                "2020-12-31 23:59:59.0",
                                new BigDecimal(35.50), "EUR");

                // Act
                Response out = priceService.checkProduct(35455, 1, "2020-06-14 21:00:00");

                // Assert
                assertEquals(valid_out, out);
        }

        @Test
        public void givenParameters_thenReturnCorrectProducts4() {
                // Arrange
                ArrayList<Price> mock_array = new ArrayList<>();

                /* Setup DB entries returns */
                Price first_entry = new Price(3, 1, Timestamp.valueOf("2020-06-15 00:00:00"),
                                Timestamp.valueOf("2020-06-15 11:00:00"), 3,
                                35455, 1, new BigDecimal(30.5),
                                "EUR");
                Price second_entry = new Price(1, 1, Timestamp.valueOf("2020-06-14 00:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                35455, 0, new BigDecimal(35.50),
                                "EUR");
                mock_array.add(first_entry);
                mock_array.add(second_entry);

                lenient().when(priceRepository.findAllEntries(35455, 1, Timestamp.valueOf("2020-06-15 10:00:00")))
                                .thenReturn(mock_array);

                Response valid_out = new Response(35455, 1, (float) 14.084503, "2020-06-15 00:00:00.0",
                                "2020-06-15 11:00:00.0",
                                new BigDecimal(30.5), "EUR");

                // Act
                Response out = priceService.checkProduct(35455, 1, "2020-06-15 10:00:00");

                // Assert
                assertEquals(valid_out, out);
        }

        @Test
        public void givenParameters_thenReturnCorrectProducts5() {
                // Arrange
                ArrayList<Price> mock_array = new ArrayList<>();

                /* Setup DB entries returns */
                Price first_entry = new Price(4, 1, Timestamp.valueOf("2020-06-15 16:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 4,
                                35455, 0, new BigDecimal(38.95),
                                "EUR");
                Price second_entry = new Price(1, 1, Timestamp.valueOf("2020-06-14 00:00:00"),
                                Timestamp.valueOf("2020-12-31 23:59:59"), 1,
                                35455, 0, new BigDecimal(35.50),
                                "EUR");
                mock_array.add(first_entry);
                mock_array.add(second_entry);

                lenient().when(priceRepository.findAllEntries(35455, 1, Timestamp.valueOf("2020-06-16 21:00:00")))
                                .thenReturn(mock_array);

                Response valid_out = new Response(35455, 1, (float) -9.7183075, "2020-06-15 16:00:00.0",
                                "2020-12-31 23:59:59.0",
                                new BigDecimal(38.95), "EUR");

                // Act
                Response out = priceService.checkProduct(35455, 1, "2020-06-16 21:00:00");

                // Assert
                assertEquals(valid_out, out);
        }
}
