package com.example.appjson.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appjson.api.PokemonApi;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<String> nomPokemon;
    private int idPokemonActual=-1;

    public MainActivityViewModel(){
        nomPokemon = new MutableLiveData<>();
    }

    public void getNomPokemon(int id){

        if(nomPokemon.getValue()==null || id!=idPokemonActual) {
            Observable.fromCallable(() -> {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Part que s'executa en Background
                Log.d("XXX", "El pokemon es diu:" + nomPokemon.getValue());
                Log.d("XXX", "Estic descarregant el nom del pokemon");
                String nom = PokemonApi.getPokemon(2);
                nomPokemon.postValue(nom);
                idPokemonActual = id;
                return 1;
            }).subscribeOn(Schedulers.io()).subscribe();
        } else {
            //nomPokemon.postValue();
        }

    }

}
