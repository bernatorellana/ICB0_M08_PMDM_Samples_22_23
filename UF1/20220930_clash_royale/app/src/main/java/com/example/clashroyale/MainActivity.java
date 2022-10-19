package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.clashroyale.adapters.CardsAdapter;
import com.example.clashroyale.databinding.ActivityMainBinding;
import com.example.clashroyale.model.Card;
import com.example.clashroyale.touch.ItemTouchHelperCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyClash;
    CardsAdapter adapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_main);

        //---------------------------------------------
        // Configuració del Universal Image Loader
        //---------------------------------------------
        // Create global configuration and initialize ImageLoader with this config
        DisplayImageOptions options =
            new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_launcher_foreground)
                    .showImageForEmptyUri(R.drawable.ic_launcher_foreground)
                    .showImageOnFail(R.drawable.ic_launcher_foreground)
                    .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
			    .build();
        ImageLoader.getInstance().init(config);



        //---------------------------------
        // Configuració del RecyclerView
        //---------------------------------
        rcyClash = findViewById(R.id.rcyClash);
        rcyClash.setLayoutManager(new LinearLayoutManager(this));
        rcyClash.setHasFixedSize(true);
        //---------------------------------
        // Creació de l'Adapter
        adapter = new CardsAdapter(Card.getCartes(),this);
        rcyClash.setAdapter(adapter);
        //----------------------------------
        // Preparem el TouchHelper per a que funcioni el Swipe i el Drag en el RecyclerView
        ItemTouchHelper ith = new ItemTouchHelper(
                                new ItemTouchHelperCallback(adapter));
        ith.attachToRecyclerView(rcyClash);
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