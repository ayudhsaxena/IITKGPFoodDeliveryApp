/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.orderrelated;
import java.util.*;

/**
 *
 * @author Ayudh
 */
public class Cart {
    private ArrayList<Order> cartOrders;

    public Cart(ArrayList<Order> cartOrders) {
        this.cartOrders = cartOrders;
    }
    
    public Cart() {
      cartOrders = new ArrayList<>();
    }

    public ArrayList<Order> getCartOrders() {
        return cartOrders;
    }

    public void setCartOrders(ArrayList<Order> cartOrders) {
        this.cartOrders = cartOrders;
    }
    
    public void addOrder(Order o){
        cartOrders.add(o);
    }
    
    
}
