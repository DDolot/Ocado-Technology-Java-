package com.ocado.bascet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private String absolutePathToConfigFile;
    private ObjectMapper objectMapper = new ObjectMapper();

    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile = absolutePathToConfigFile;
    }

    public Map<String, List<String>> split(List<String> items) {

        Map<String, List<String>> productMap = loadConfigFile();


        Map<String, List<String>> optimalSplit = new HashMap<>();

        List<String> remainingItems = new ArrayList<>(items);
        while (!remainingItems.isEmpty()) {
            Map<String, Integer> occurrencesMap = calculateOccurances(remainingItems, productMap);
            List<String> priorityMethods = PriorityDeliveryMethod(occurrencesMap);
            String priorityMethod = priorityMethods.get(0);

            for (Iterator<String> iterator = remainingItems.iterator(); iterator.hasNext(); ) {
                String cartProduct = iterator.next();
                List<String> methodsForProduct = productMap.get(cartProduct);
                if (methodsForProduct.contains(priorityMethod)) {
                    optimalSplit.putIfAbsent(priorityMethod, new ArrayList<>());
                    optimalSplit.get(priorityMethod).add(cartProduct);
                    iterator.remove();

                }
            }
        }

        return optimalSplit;
    }

    private Map<String, Integer> calculateOccurances(List<String> items, Map<String, List<String>> productMap) {
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String cartProduct : items) {
            List<String> methods = productMap.get(cartProduct);

            if (methods != null) {
                for (String method : methods) {
                    occurrencesMap.put(method, occurrencesMap.getOrDefault(method, 0) + 1);
                }
            }
        }
        return occurrencesMap;
    }

    private List<String> PriorityDeliveryMethod(Map<String, Integer> occurrencesMap) {
        List<String> MethodsDescendingOrder = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortedOccurrences = new ArrayList<>(occurrencesMap.entrySet());
        sortedOccurrences.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : sortedOccurrences) {
            String method = entry.getKey();

            MethodsDescendingOrder.add(method);
        }
        return MethodsDescendingOrder;
    }


    private Map<String, List<String>> loadConfigFile () {

        try {
            JsonNode configJson = objectMapper.readTree(new File(absolutePathToConfigFile));
            Map<String, List<String>> productsMap = new HashMap<>();

            configJson.fields().forEachRemaining(entry -> {
                String product = entry.getKey();
                List<String> deliveryMethods = new ArrayList<>();
                entry.getValue().elements().forEachRemaining(delivery -> {
                    deliveryMethods.add(delivery.asText());
                });
                productsMap.put(product, deliveryMethods);
            });

            return productsMap;
        } catch (IOException e) {
            throw new RuntimeException("Error loading config file", e);
        }

    }

}