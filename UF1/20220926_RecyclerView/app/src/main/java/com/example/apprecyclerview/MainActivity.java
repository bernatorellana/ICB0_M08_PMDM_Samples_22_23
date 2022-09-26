package com.example.apprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.apprecyclerview.model.Client;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1.- Busquem el recycler
        rcyClients = findViewById(R.id.rcyClients);
        // 2.- Assignar un layout al recycler ( vas en horitzontal , vertical o una graella )
        rcyClients.setLayoutManager(new LinearLayoutManager(this));
        rcyClients.hasFixedSize();
        // 3.- Crear llista de clients
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1,"Paco"));
        clients.add(new Client(2,"Maria"));
        clients.add(new Client(3,"Cristina"));
        // 4.- Crear adapter
        ClientsAdapter adapter = new ClientsAdapter(clients);
        // 5 .- Assignar l'adapter al recyclerview
        rcyClients.setAdapter(adapter);

    }
}