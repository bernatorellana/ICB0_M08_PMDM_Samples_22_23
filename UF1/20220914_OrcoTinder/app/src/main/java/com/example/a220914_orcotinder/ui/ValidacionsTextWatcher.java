package com.example.a220914_orcotinder.ui;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.a220914_orcotinder.R;
import com.example.a220914_orcotinder.model.Ork;

public class ValidacionsTextWatcher implements TextWatcher {
    private EditText editText;

    public ValidacionsTextWatcher(EditText editText){
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
            if(this.editText.getId()== R.id.edtName){
                pintaBackground(Ork.validaNom(editText.getText().toString()));
            } else if(this.editText.getId()== R.id.edtPhone){
                pintaBackground(Ork.validaPhone(editText.getText().toString()));
            }
    }

    private void pintaBackground(boolean validaNom) {
        editText.setBackgroundColor(validaNom? Color.TRANSPARENT:Color.RED);
    }
}
