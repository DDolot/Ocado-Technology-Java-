package config;

import java.util.List;
import java.util.Map;

public class ProductConfig {
    private Map<String, List<String>> products;


    public Map<String, List<String>> getProducts() {
        return products;
    }

    public void setProducts(Map<String, List<String>> products) {
        this.products = products;
    }
}
