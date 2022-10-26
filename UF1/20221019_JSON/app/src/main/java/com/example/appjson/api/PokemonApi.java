package com.example.appjson.api;

import android.util.Log;
import android.widget.EditText;

import com.example.appjson.R;
import com.example.appjson.model.Tipus;
import com.example.appjson.utils.NetworkUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonApi {

    public static String getPokemon(int id)
    {
        String nom = null;
        String json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/pokemon/"+id);

        //-------------------------------------------------------------
        try {
            JSONObject pokemonObj = new JSONObject(json);
            nom = pokemonObj.getString("name");
            //int id = pokemonObj.getInt("id");
            JSONObject speciesObj = pokemonObj.getJSONObject("species");
            String urlSpecies = speciesObj.getString("url");


        } catch (JSONException e) {
            Log.e("XXX", "He petat com un cabr....");
        }
        //---------------------------------------
        //--------------GSON -------------------------
        //---------------------------------------
        Gson gson = new Gson();
        json = NetworkUtils.getJSon("https://pokeapi.co/api/v2/type");
        Tipus tipus = gson.fromJson(json, Tipus.class);

        return nom;
    }
}
