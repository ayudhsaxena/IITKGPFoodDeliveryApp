/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.users;
import com.example.onlinefooddeliveryapp.orderrelated.Menu;
import com.example.onlinefooddeliveryapp.orderrelated.Order;

import java.util.*;

/**
 *
 * @author Ayudh
 */
public class HallCanteen extends User{
    private Menu menuName;
    private ArrayList<Order> runningOrders;

    public HallCanteen(Menu menuName, ArrayList<Order> runningOrders, HashMap m) {
        super(m);
        this.menuName = menuName;
        this.runningOrders = runningOrders;
    }

    public Menu getMenuName() {
        return menuName;
    }

    public void setMenuName(Menu menuName) {
        this.menuName = menuName;
    }

    public ArrayList<Order> getRunningOrders() {
        return runningOrders;
    }

    public void setRunningOrders(ArrayList<Order> runningOrders) {
        this.runningOrders = runningOrders;
    }
    
    
    
    
}
