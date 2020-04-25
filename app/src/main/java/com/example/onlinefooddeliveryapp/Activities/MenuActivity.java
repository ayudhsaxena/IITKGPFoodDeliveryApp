package com.example.onlinefooddeliveryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Adapters.MyfoodRecyclerViewAdapter;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Menu;
import com.example.onlinefooddeliveryapp.orderrelated.foodItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    public static HashMap<String, MyHashMap<Integer, Integer>> allHalls = new HashMap<String, MyHashMap<Integer, Integer>>() {{
        put("RP", new MyHashMap<Integer, Integer>(0));
        put("RK", new MyHashMap<Integer, Integer>(0));
        put("MS", new MyHashMap<Integer, Integer>(0));
        put("VS", new MyHashMap<Integer, Integer>(0));
        put("LLR", new MyHashMap<Integer, Integer>(0));
        put("MMM", new MyHashMap<Integer, Integer>(0));
        put("Azad", new MyHashMap<Integer, Integer>(0));
        put("Nehru", new MyHashMap<Integer, Integer>(0));
        put("Patel", new MyHashMap<Integer, Integer>(0));
        put("MT", new MyHashMap<Integer, Integer>(0));
        put("HJB", new MyHashMap<Integer, Integer>(0));
    }};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private CollectionReference collRef = FirebaseFirestore.getInstance().collection("menus");
    public static FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mFragmentManager = getSupportFragmentManager();
        final Intent intent = getIntent();
        final ArrayList<foodItem> menuList = new ArrayList<>();
        Query query = collRef.whereEqualTo("hall_name", intent.getStringExtra("hall_name"));
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty())
                        Log.d("menu", "Hall doesn't exist!");
                    else {
                        DocumentSnapshot doc = task.getResult().getDocuments().get(0);
                        ArrayList<Map<String, Object>> mapList = (ArrayList<Map<String, Object>>) doc.get("food_items");
                        for (Map<String, Object> m : mapList) {
                            String itemName = (String) m.get("name");
                            int cost = ((Long) m.get("cost")).intValue();
                            int id = ((Long) m.get("id")).intValue();
                            String label = (String) m.get("label");
                            foodItem item = new foodItem(itemName, -1, cost, id, label);
                            menuList.add(item);
                        }
                        Menu mMenu = new Menu(intent.getStringExtra("hall_name"), menuList);
                        recyclerView = findViewById(R.id.item_list);
                        recyclerView.setHasFixedSize(true);

                        recyclerView.setLayoutManager(layoutManager);
                        mAdapter = new MyfoodRecyclerViewAdapter(mMenu);
                        recyclerView.setAdapter(mAdapter);

                    }
                } else {
                    Log.d("menu", "Error getting documents");
                }
            }
        });


    }

    public static class MyHashMap<K, V> extends HashMap<K, V> {
        protected V defualtValue;

        public MyHashMap(V defualtValue) {
            this.defualtValue = defualtValue;
        }

        @Override
        public V get(Object K) {
            return containsKey(K) ? super.get(K) : defualtValue;
        }

    }


}
