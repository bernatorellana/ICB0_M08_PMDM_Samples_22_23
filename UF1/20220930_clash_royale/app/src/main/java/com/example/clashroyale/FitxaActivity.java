package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clashroyale.model.Card;

public class FitxaActivity extends AppCompatActivity {

    public static final String CARTA = "carta";
    private Card mCard;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imvPhoto = findViewById(R.id.imvPhoto);
            imvPhoto.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa);
        // recuperar els paràmetres que en passen
        // via l'Intent.
        mCard = getIntent().getParcelableExtra(CARTA);
        TextView txvNom = findViewById(R.id.txvNom);
        txvNom.setText(mCard.getName());


        Button btnPhoto = findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:41.58444656874773, 1.6010902419077437");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                //if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                //}

                /*
                Intent takePictureIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }*/
            }
        });



    }
}