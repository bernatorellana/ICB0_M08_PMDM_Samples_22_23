package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.clashroyale.adapters.CardsAdapter;
import com.example.clashroyale.model.Card;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyClash;
    CardsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcyClash = findViewById(R.id.rcyClash);
        rcyClash.setLayoutManager(new LinearLayoutManager(this));
        rcyClash.setHasFixedSize(true);
        //---------------------------------
        adapter = new CardsAdapter(Card.getCartes());
        rcyClash.setAdapter(adapter);
        

    }
}