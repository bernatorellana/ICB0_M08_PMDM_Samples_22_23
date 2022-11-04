package com.example.appjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.example.appjson.viewmodel.MainActivityViewModel;
import java.io.File;


public class MainActivity extends AppCompatActivity {

    RelativeLayout pgrDownload;
    EditText edtJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtJSON =findViewById(R.id.edtJSON);
        MainActivityViewModel viewmodel =
                new ViewModelProvider(this).get(MainActivityViewModel.class);
        //-----------------------------------------
        // Crear una carpeta dins de la carpeta de l'aplicació
        File jsonFolder = new File( this.getFilesDir(), "jsons");
        jsonFolder.mkdirs(); // creem la carpeta explícitament
        viewmodel.descarregaPokemons(jsonFolder); // i li passem a la funció de descàrrega

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sp = this.getPreferences(Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("valor", edtJSON.getText()+"");
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = this.getPreferences(Activity.MODE_PRIVATE);

        String v = sp.getString("valor","<null>");
        edtJSON.setText(v);
    }
}