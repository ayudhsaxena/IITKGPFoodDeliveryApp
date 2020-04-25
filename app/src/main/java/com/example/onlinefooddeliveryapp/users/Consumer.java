/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.users;
import com.example.onlinefooddeliveryapp.orderrelated.Cart;
import com.example.onlinefooddeliveryapp.orderrelated.Order;

import java.util.*;
/**
 *
 * @author Ayudh
 */
public class Consumer extends User {
    private ArrayList<Order> placedOrders;
    private Cart myCart;
    
    public Consumer(HashMap m){
        super(m);
        myCart = new Cart();
        placedOrders = new ArrayList<>();
    }

    public ArrayList<Order> getPlacedOrders() {
        return placedOrders;
    }

    public void setPlacedOrders(ArrayList<Order> placedOrders) {
        this.placedOrders = placedOrders;
    }

    public Cart getMyCart() {
        return myCart;
    }

    public void setMyCart(Cart myCart) {
        this.myCart = myCart;
    }
    
    public void addPlacedOrder(Order o){
       placedOrders.add(o);
    }
}
