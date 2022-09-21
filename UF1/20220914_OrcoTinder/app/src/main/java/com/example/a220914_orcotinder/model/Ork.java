package com.example.a220914_orcotinder.model;

import android.telephony.PhoneNumberUtils;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import com.example.a220914_orcotinder.R;

import java.util.ArrayList;
import java.util.List;

public class Ork {

    private String name;
    private String phone;
    private Sexe sexe;
    private @IdRes  int imatge;
    private int indexPais;

    //------------------------------
    private static List<Ork> orks;
    public static List<Ork> getOrks() {
        if (orks==null){
            orks = new ArrayList<>();
            orks.add(new Ork("Orkito", "123123321", Sexe.HOME, R.drawable.orco, 2));
            orks.add(new Ork("Orkita", "453423342", Sexe.DONA, R.drawable.ork1, 2));
            orks.add(new Ork("Orkin", "453423342", Sexe.DONA, R.drawable.ork2, 2));
        }
        return orks;
    }
    //------------------------------

    public Ork(String name, String phone, Sexe sexe, int imatge, int codi) {
        setName(name);
        setPhone(phone);
        setSexe(sexe);
        setImatge(imatge);
        setIndexPais(codi);
    }

    public static boolean validaPhone(String phoneNumber) {
        return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public static boolean validaNom(String name) {
        return !(name ==null || name.trim().length()<3);
    }

    public void setName(String name) {
        if(!validaNom(name)) throw new RuntimeException("Nom incorrecte");
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public int getImatge() {
        return imatge;
    }

    public void setImatge(int imatge) {
        this.imatge = imatge;
    }

    public int getIndexPais() {
        return indexPais;
    }


    public void setIndexPais(int indexPais) {
        this.indexPais = indexPais;
    }





}
