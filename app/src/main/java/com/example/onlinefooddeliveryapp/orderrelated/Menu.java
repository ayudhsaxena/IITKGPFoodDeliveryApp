/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onlinefooddeliveryapp.orderrelated;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.onlinefooddeliveryapp.Activities.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ayudh
 */
public class Menu {
    private ArrayList<foodItem> menuList;

    private String hallName;

    public Menu(String hallName,ArrayList<foodItem> menuList) {
        this.menuList = menuList;
        this.hallName = hallName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public ArrayList<foodItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<foodItem> menuList) {
        this.menuList = menuList;
    }

    public int getSize(){
        return menuList.size();
    }
    public foodItem getItem(int pos){
        return menuList.get(pos);
    }
    
    
    
}
