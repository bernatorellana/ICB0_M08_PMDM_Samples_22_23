package com.example.appjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.appjson.api.PokemonApi;
import com.example.appjson.model.Tipus;
import com.example.appjson.utils.NetworkUtils;
import com.example.appjson.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RelativeLayout pgrDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgrDownload = findViewById(R.id.pgrDownload);

        MainActivityViewModel viewmodel =
                new ViewModelProvider(this).get(MainActivityViewModel.class);

        pgrDownload.setVisibility(View.VISIBLE);
        viewmodel.getNomPokemon(2);
        viewmodel.nomPokemon.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String nom) {
                EditText edtJSON = findViewById(R.id.edtJSON);
                edtJSON.setText(nom);
                pgrDownload.setVisibility(View.INVISIBLE);
            }
        });




    /*
        Observable.fromCallable(() -> {
            // Part que s'executa en Background
            String nom = PokemonApi.getPokemon(2);
            return nom;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((nom) -> {
                    // això es crida quan acaba el fil
                    // i s'executa en el fil d'interfície gràfica
                    EditText edtJSON = findViewById(R.id.edtJSON);
                    edtJSON.setText(nom);
                }
        );
        */

    }
}