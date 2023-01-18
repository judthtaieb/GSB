package fr.euroforma.gsb.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import fr.euroforma.gsb.R;

public class ParamActivity extends AppCompatActivity {
    public static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    public static final String CODE_VISITEUR = "CODE_VISITEUR";
    private static final String EMAIL = "EMAIL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);

    }


}