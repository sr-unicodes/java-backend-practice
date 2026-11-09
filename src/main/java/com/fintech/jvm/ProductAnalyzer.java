package com.fintech.jvm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Products{
    String name;
    String category;
    double price;

    public Products(String name, String category, double price){
        this.name = name;
        this.category = category;
        this.price = price;
    }
}


public class ProductAnalyzer {
    public static void main(String[] args){
        List<Products> inventory = Arrays.asList(new Products("Laptop", "Electronics", 1200.00),
                new Products("Coffee Maker", "Appliance", 80.00),
                new Products("Headphones", "Electronics", 150.00),
                new Products("Smartphone", "Electronics", 800.00),
                new Products("Desk Chair", "Furniture", 200.00));

        List<String> premiumElectronics = inventory.stream()
                .filter(P-> P.category.equals("Electronics"))
                .filter(p -> p.price > 500.00)
                .map(p-> p.name.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(premiumElectronics);
    }

}
