package org.milainfontanals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicialitzarImageLoader();


    }

    private void inicialitzarImageLoader() {
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
    }
}