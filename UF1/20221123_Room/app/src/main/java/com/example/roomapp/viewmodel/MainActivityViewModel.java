package com.example.roomapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.roomapp.db.AppDatabase;
import com.example.roomapp.db.daos.UserDao;
import com.example.roomapp.db.entities.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends AndroidViewModel {

    public MutableLiveData< List<User> > llistaUsuaris;

    public MainActivityViewModel(@NonNull Application application) {

        super(application);
        llistaUsuaris = new MutableLiveData<>();
    }

    public void llancaQueryLlistaUsuaris(){
        if(llistaUsuaris.getValue()==null) {
            Observable.fromCallable(() -> {
                // això s'executa en un fil
                AppDatabase db = Room.databaseBuilder(getApplication().getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                UserDao dao = db.userDao();

            /*User u = new User(12,"Paco","Garcia");
            dao.insertAll(u);
            u.firstName="Paquete";
            dao.update(u);*/
                List<User> usuaris = dao.getAll();
                Log.d("XXX", "QUERY LLANÇADA:" + usuaris);

                llistaUsuaris.postValue(usuaris);

                return usuaris;
            }).subscribeOn(Schedulers.io()).subscribe();
        }
    }


}
