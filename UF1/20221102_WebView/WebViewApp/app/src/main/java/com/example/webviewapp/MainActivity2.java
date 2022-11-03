package com.example.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.webviewapp.databinding.ActivityMain2Binding;
import com.example.webviewapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.webPage.loadUrl("file:///android_asset/index.html");
/*
        ArrayList<String> persones = new ArrayList<>();
        persones.add("Maria");
        persones.add("Joan");
        persones.add("Pep");
        persones.add("Carme");
        persones.add("Cristina");
        String HTML = "<html><body><h1>Hola món</h1><ul>";
        for(String nom: persones) {
            HTML += "<li>"+nom+"</li>";
        }
        HTML += "</ul></body></html>";
        binding.webPage.loadData(HTML, "text/html", "utf-8");
*/
    }
}