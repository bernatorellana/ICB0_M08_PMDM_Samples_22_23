package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        //----------------------------------
        // Activem el toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar));

    }

    /**
     * Gestió de la creació del menú dins de la barra
     * d'aplicació.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mniEsborrar:
                adapter.esborraActual();
                return true;
            case R.id.mniDown:
                adapter.downSelected();
                return true;
            case R.id.mniUp:
                adapter.upSelected();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}