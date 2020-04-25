/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.users;
import com.example.onlinefooddeliveryapp.orderrelated.Order;


import java.util.*;

/**
 *
 * @author Ayudh
 */
public class DeliveryPerson extends User {
    private ArrayList<Order> ordersReceived;
    private int status;
    private int idleTime;
    private final HashMap<Integer,String> list = new HashMap<Integer,String>(){{
        put(0,"Busy");
        put(1,"Available");
        put(2,"Not Available");
    }};

    public DeliveryPerson(ArrayList<Order> ordersReceived, int status, int idleTime, HashMap m) {
        super(m);
        this.ordersReceived = ordersReceived;
        this.status = status;
        this.idleTime = idleTime;
    }
    

    public ArrayList<Order> getOrdersReceived() {
        return ordersReceived;
    }

    public void setOrdersReceived(ArrayList<Order> ordersReceived) {
        this.ordersReceived = ordersReceived;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }
    
    
    
    
    
    
}
