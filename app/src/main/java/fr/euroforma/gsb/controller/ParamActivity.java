package fr.euroforma.gsb.controller;


import static fr.euroforma.gsb.controller.AuthentificationActivity.SHARED_PREF_USER_INFO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.euroforma.gsb.R;


public class ParamActivity extends AppCompatActivity {
    EditText nom,prenom,mcode,email,url;
    Button enregistrer;

    public static final String SHARED_PREF_USER_INFO= "SHARED_PREF_USER_INFO";
    private static final String NOM = "NOM";
    private static final String PRENOM = "PRENOM";
    public static final String CODE_VISITEUR = "CODE_VISITEUR";
    private static final String EMAIL = "EMAIL";
    private static final String URL = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);


        nom =  findViewById(R.id.nom);
        prenom =  findViewById(R.id.prenom);
        mcode =  findViewById(R.id.mcode);
        email =  findViewById(R.id.email);
        url =  findViewById(R.id.url);
        enregistrer = findViewById(R.id.enregistrer);

        nom.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(NOM, ""));
        prenom.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(PRENOM, ""));
        mcode.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(CODE_VISITEUR, ""));
        email.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(EMAIL, ""));
        url.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(URL, ""));
    }



        

        void afficher(String msg) {Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}

        public void clickEnregistrer(View view){
            if (nom.getText().toString().trim().length() == 0||prenom.getText().toString().trim().length() == 0
            || mcode.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0
            || url.getText().toString().trim().length() == 0) {
                afficher("champs vides");
            }else {
                afficher("les parametres ont bien été renseignés");
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(NOM, nom.getText().toString())
                        .putString(PRENOM, prenom.getText().toString())
                        .putString(CODE_VISITEUR, mcode.getText().toString())
                        .putString(EMAIL, email.getText().toString())
                        .putString(URL, url.getText().toString())
                        .apply();


                afficher("Informations enregistrées avec succès ");

            }
        }

}
