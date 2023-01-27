package com.example.c15sucks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IGameListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frmMain = findViewById(R.id.frmMain);
        C15View c15 = new C15View(this, this);
        frmMain.addView(c15);
    }

    @Override
    public void onGameIsOver() {

    }

    @Override
    public int onPlayerWin() {
        return 0;
    }

    @Override
    public void onMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setTitle("Atenció:");


        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();*/
    }

}