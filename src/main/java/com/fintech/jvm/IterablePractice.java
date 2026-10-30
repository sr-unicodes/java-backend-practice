package com.fintech.jvm;

import java.util.*;

class ShoppingCart implements Iterable<String>  {
    private List<String> items = new ArrayList<>();


    public void addProduct(String item){
       items.add(item);
    }

    @Override
     public Iterator<String> iterator(){
        return items.iterator();
        }
}

public class IterablePractice {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct("Laptop");
        cart.addProduct("Headphones");
        cart.addProduct("Keyboard");

        for(String item : cart){
            System.out.println(item);
        }
    }
}
