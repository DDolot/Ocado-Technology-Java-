package com.ocado.bascet;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasketSplitterTest {

    private final String path = "C:\\Users\\DOLOTD\\Desktop\\OcadoRecruitment\\src\\main\\resources\\config.json";
    @Test
    public void TaskExampleTest(){

            BasketSplitter basketSplitter = mock(BasketSplitter.class);

            List<String> produkty = List.of(
                    "Steak (300g)",
                    "Carrots (1kg)",
                    "Soda (24x330ml)",
                    "AA Battery (4 Pcs.)",
                    "Espresso Machine",
                    "Garden Chair"
            );

            Map<String, List<String>> expectedDeliveryOptions = new HashMap<>();
            expectedDeliveryOptions.put("Express Delivery", Arrays.asList("Steak (300g)", "Carrots (1kg)", "Soda (24x330ml)", "AA Battery (4 Pcs.)"));
            expectedDeliveryOptions.put("Courier", Arrays.asList("Espresso Machine", "Garden Chair"));

            when(basketSplitter.split(produkty)).thenReturn(expectedDeliveryOptions);

            Map<String, List<String>> actualDeliveryOptions = basketSplitter.split(produkty);

            assertEquals(expectedDeliveryOptions, actualDeliveryOptions);
        }
        @Test
        public void OwnExampleTest(){

            BasketSplitter basketSplitter = new BasketSplitter(path);


            Map<String, List<String>> expectedDeliveryOptions = Map.of(
                    "Pick-up point", Arrays.asList("Cookies Oatmeal Raisin", "Cheese Cloth"),
                    "In-store pick-up", Arrays.asList("Sole - Dover, Whole, Fresh")
            );

            List<String> products = List.of("Cookies Oatmeal Raisin","Cheese Cloth","Sole - Dover, Whole, Fresh");


            Map<String, List<String>> actualDeliveryOptions = basketSplitter.split(products);


            assertEquals(expectedDeliveryOptions, actualDeliveryOptions);
        }

}