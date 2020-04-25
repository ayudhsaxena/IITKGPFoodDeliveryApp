/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.orderrelated;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ayudh
 */
public class Order {
    private String source;
    private String destination;
    private ArrayList<foodItem> items;
    private orderTracker tracker;
    private float totalCost;
    private Date date;

    public Order(String source, String destination, ArrayList<foodItem> items,int totalCost,Date date) {
        this.source = source;
        this.destination = destination;
        this.items = items;
        tracker = new orderTracker();
        this.totalCost = totalCost;
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public orderTracker getTracker() {
        return tracker;
    }

    public void setTracker(orderTracker T) {
        this.tracker = T;
    }
    
    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ArrayList<foodItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<foodItem> items) {
        this.items = items;
    }
    
    public void addItem(foodItem i){
       items.add(i);
       totalCost += i.getCost();
    }
    
    public void updateTracker(){
        tracker.update();
    }
    
    

    
}
