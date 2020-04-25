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
public class orderTracker {
    private int status;
    private final HashMap<Integer,String> list = new HashMap<Integer,String>(){{
        put(0,"In Cart");
        put(1,"Order Placed");
        put(2,"Cooking");
        put(3, "Out for Delivery");
        put(4, "Delivered");
        put(5, "Cancelled");
    }};
    
    public orderTracker(){
        status = 0;
    }
    
    public int getStatus(){
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void update(){
        status++;
    }
    
}
