package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.roomapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewmodel;

    private final static String TAG = "XXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-------------------------------------------------------
        // Recuperar el ViewModel
        viewmodel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewmodel.llancaQueryLlistaUsuaris();
        viewmodel.llistaUsuaris.observe(this, usuaris ->{
            Log.d(TAG, "OBSERVED: "+usuaris);
        } );
    }
}