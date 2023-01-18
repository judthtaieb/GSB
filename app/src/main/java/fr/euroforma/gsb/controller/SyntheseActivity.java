package fr.euroforma.gsb.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.euroforma.gsb.R;

public class SyntheseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese);
    }
    public void retourMenu(View view) {
        Intent retourIntent= new Intent(SyntheseActivity.this, MenuActivity.class);
        startActivity(retourIntent);
    }
}