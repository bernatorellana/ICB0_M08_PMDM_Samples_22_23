package com.example.appjson.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.appjson.utils.NetworkUtils;
import java.io.File;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {


    public MainActivityViewModel(){
    }

    public void descarregaPokemons(File jsonFolder){

            Observable.fromCallable(() -> {

                String json = NetworkUtils.getJSon(
                        jsonFolder,
                        "pokemons.json",
                        "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=30");

                return 1;
            }).subscribeOn(Schedulers.io()).subscribe();
        }
}
