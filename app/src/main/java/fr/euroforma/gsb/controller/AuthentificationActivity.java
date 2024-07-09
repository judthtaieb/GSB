package fr.euroforma.gsb.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import fr.euroforma.gsb.R;
import android.os.Process;

public class AuthentificationActivity extends AppCompatActivity {
    EditText codeVisiteur;
    LinearLayout codeVerif;
    EditText mEmail;
    EditText mCodeVerif;

    Button btnEnvoyer;
    Button btnValider;
    Random r = new Random();
    int min = 10000;
    int max = 99999;
    Integer codeAleatoire = r.nextInt((max - min) + 1) + min;
    public static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    public static final String CODE_VISITEUR = "CODE_VISITEUR";
    private static final String EMAIL = "EMAIL";
    private static final String ECHEC = "ECHEC";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        getSharedPreferences(SHARED_PREF_USER_INFO,MODE_PRIVATE ).edit().clear().commit();

        codeVisiteur = findViewById(R.id.codeVisiteur);
        codeVerif = findViewById(R.id.verifiaction_layout);
        mCodeVerif = findViewById(R.id.codeVerif);
        mEmail = findViewById(R.id.mEmail);

        btnValider = findViewById(R.id.btnValider);
        btnEnvoyer = findViewById(R.id.btnEnvoyer);




    }

    void afficheCode(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clickEnvoyer(View view) {

        afficheCode(codeAleatoire.toString());
        codeVerif.setVisibility(View.VISIBLE);

    }

    void afficheMessageVerif(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clickVerification(View view) {
        String s1 = codeAleatoire.toString();
        String echec = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(ECHEC, "0");
        int nbechec=  Integer.parseInt(echec);
        //au bout de 3 echecs (on a commence a 0)
        if (nbechec >= 2)
        {    afficheMessageVerif(" nombre d essais  maximum atteint.authentification bloquée.  ");
            btnEnvoyer.setEnabled(false);
        }
            if (s1.equals(mCodeVerif.getText().toString())) {
                afficheMessageVerif("le code entré est valide");
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(CODE_VISITEUR, codeVisiteur.getText().toString())
                        .putString(EMAIL, mEmail.getText().toString())
                        .apply();
                String CodeVisiteurEnregistre = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(CODE_VISITEUR, null);
                String EmailEnregistre = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(EMAIL, null);
                afficheMessageVerif("Code  visiteur enregistré: " + CodeVisiteurEnregistre.toString());
                afficheMessageVerif("Email enregistré: " + EmailEnregistre.toString());

                Intent menuIntent = new Intent(AuthentificationActivity.this, MenuActivity.class);
                startActivity(menuIntent);
            } else {
                afficheMessageVerif("le code entré n'est  pas valide");
                nbechec++;
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(ECHEC,String.valueOf(nbechec))
                        .apply();

            }



    }
    public void retourMenu(View view) {
        Intent retourIntent= new Intent(AuthentificationActivity.this, MenuActivity.class);
        startActivity(retourIntent);
    }
}


