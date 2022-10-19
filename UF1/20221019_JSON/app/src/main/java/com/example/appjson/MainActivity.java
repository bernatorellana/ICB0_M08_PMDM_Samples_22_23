package com.example.appjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;

import com.example.appjson.model.Tipus;
import com.example.appjson.utils.NetworkUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------
        // DANGER
        //            ______
        //        .-"      "-.
        //       /            \
        //      |              |
        //      |,  .-.  .-.  ,|
        //      | )(__/  \__)( |
        //      |/     /\     \|
        //      (_     ^^     _)
        //       \__|IIIIII|__/
        //        | \IIIIII/ |
        //        \          /
        //  jgs    `--------`
        //------------------------------------------------------------------
        // Desactivació de la prohibició de descarregar en el fil principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //------------------------------------------------------------------
        String json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/pikachu");
        EditText edtJSON = findViewById(R.id.edtJSON);

        //-------------------------------------------------------------
        try {
            JSONObject pokemonObj = new JSONObject(json);
            String nom = pokemonObj.getString("name");
            int id = pokemonObj.getInt("id");
            JSONObject speciesObj = pokemonObj.getJSONObject("species");
            String urlSpecies = speciesObj.getString("url");

            edtJSON.append("nom:"+nom+"\n");
            edtJSON.append("id:"+id+"\n");
            edtJSON.append("urlSpecies:"+urlSpecies+"\n");

        } catch (JSONException e) {
            Log.e("XXX", "He petat com un cabr....");
        }
        //---------------------------------------
        //--------------GSON -------------------------
        //---------------------------------------
        Gson gson = new Gson();
        json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/type");
        Tipus tipus = gson.fromJson(json, Tipus.class);
        edtJSON.append("hi ha "+tipus.getCount()+"tipus de pokemons");
        edtJSON.append(tipus.getResults().get(0).getName());


    }
}