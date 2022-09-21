package com.example.a220914_orcotinder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.a220914_orcotinder.databinding.ActivityMainBinding;
import com.example.a220914_orcotinder.databinding.FitxaOrcoBinding;
import com.example.a220914_orcotinder.model.Ork;
import com.example.a220914_orcotinder.model.Sexe;
import com.example.a220914_orcotinder.ui.ValidacionsTextWatcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> paisos = new ArrayList<>();
    EditText edtName;
    EditText edtPhone;
    RadioGroup rdgSexe;
    RadioButton rdbHome;
    RadioButton rdbDona;
    Spinner spnPais;
    ImageButton btnPrevious;
    ImageButton btnNext;
    ImageView imvOgro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--------------------------------------------------
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        rdgSexe = findViewById(R.id.rdgSexe);
        rdbHome = findViewById(R.id.rdbHome);
        rdbDona = findViewById(R.id.rdbDona);
        spnPais = findViewById(R.id.spnPais);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        imvOgro = findViewById(R.id.imvOgro);
        //--------------------------------------------------------
        programarBotons();
        programarValidacions();
        carregaSpinner();
        mostraOrkActual();
    }

    private void programarValidacions() {
        ValidacionsTextWatcher vtwName = new ValidacionsTextWatcher(edtName);
        ValidacionsTextWatcher vtwPhone = new ValidacionsTextWatcher(edtPhone);
        edtName.addTextChangedListener(vtwName);
        edtPhone.addTextChangedListener(vtwPhone);
    }

    private void programarBotons() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desarDades();
                indexOrkActual= (indexOrkActual+1)%Ork.getOrks().size();
                mostraOrkActual();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desarDades();
                indexOrkActual--;
                if(indexOrkActual<0) indexOrkActual = Ork.getOrks().size()-1;
                mostraOrkActual();
            }
        });
    }

    private void desarDades() {
        Ork orcActual = getOrcActual();
        orcActual.setName(edtName.getText().toString());
        orcActual.setPhone(edtPhone.getText().toString());
        orcActual.setSexe(
                rdgSexe.getCheckedRadioButtonId()==R.id.rdbDona?Sexe.DONA:Sexe.HOME);
        orcActual.setIndexPais(spnPais.getSelectedItemPosition());
    }

    private Ork getOrcActual() {
        return Ork.getOrks().get(indexOrkActual);
    }

    private void carregaSpinner() {
        paisos.add("Andorra");
        paisos.add("França");
        paisos.add("Uzbequistan");

        ArrayAdapter<String> adapterPaisos = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paisos);
        spnPais.setAdapter(adapterPaisos);
    }

    private int indexOrkActual = 1;

    private void mostraOrkActual() {
        Ork orkActual = getOrcActual();
        edtName.setText(orkActual.getName());
        edtPhone.setText(orkActual.getPhone());
        rdgSexe.check(
                orkActual.getSexe() == Sexe.DONA ?
                        R.id.rdbDona :
                        R.id.rdbHome);
        spnPais.setSelection(orkActual.getIndexPais());
        imvOgro.setImageResource(orkActual.getImatge());

        btnPrevious.setEnabled(indexOrkActual!=0);
        btnNext.setEnabled(indexOrkActual<Ork.getOrks().size()-1);
    }
}
