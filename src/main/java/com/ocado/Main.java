package com.ocado;

import com.ocado.bascet.BasketSplitter;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter(
                "C:\\Users\\DOLOTD\\Desktop\\OcadoRecruitment\\src\\main\\resources\\config.json"
        );

        List<String> p = List.of("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
        List<String> d = List.of("Cookies Oatmeal Raisin","Cheese Cloth","Sole - Dover, Whole, Fresh");

//        List<String> produkty = List.of(
//                "Steak (300g)",
//                "Carrots (1kg)",
//                "Cold Beer (330ml)",
//                "AA Battery (4 Pcs.)",
//                "Espresso Machine",
//                "Garden Chair"
//        );

        System.out.println(basketSplitter.split(d));

//


    }
}