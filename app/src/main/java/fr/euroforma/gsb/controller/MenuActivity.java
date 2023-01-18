package fr.euroforma.gsb.controller;

import static fr.euroforma.gsb.controller.AuthentificationActivity.CODE_VISITEUR;
import static fr.euroforma.gsb.controller.AuthentificationActivity.SHARED_PREF_USER_INFO;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.euroforma.gsb.R;

public class MenuActivity extends AppCompatActivity {
    private TextView menuTextView;
    private Button fraisForfaitButton;
    private Button fraisHorsForfaitButton;
    private Button syntheseDesFraisButton;
    private Button parametresButton;
    private Button deconnexionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secure();
        setContentView(R.layout.activity_main);




    }


    public void clickMenu(View view) {
        Class mc = null;
        switch (view.getId()) {
            case R.id.btnFraisForfait:
                afficheMenu("mes frais forfaits");
                mc= FraisFActivity.class;
                break;
            case R.id.btnFraisHorsForfait:
                afficheMenu("mes frais hors forfaits");
                mc= FraisHFActivity.class;
                break;
            case R.id.btnSyntheseDesFrais:
                afficheMenu("synthese de mes frais");
                mc= SyntheseActivity.class;
                break;
            case  R.id.btnParametres:
                afficheMenu("mes informations personnelles");
                mc= ParamActivity.class;
                break;
            case  R.id.btnHistoriqueEnvois:
                afficheMenu("mes envois");
                mc=HistoriqueActivity.class;
                break;
            case  R.id.btnDeconnexion:
                afficheMenu("mon authentification");
                mc=AuthentificationActivity.class;
                break;

        }
        Intent menuIntent= new Intent(MenuActivity.this,mc);
        startActivity(menuIntent);
    }
    void afficheMenu(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void secure(){
        String cvisiteur =getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString("CODE_VISITEUR","Entrez votre code visiteur");
        afficheMenu(cvisiteur);
        if (cvisiteur.toString().equals("Entrez votre code visiteur")){

            Intent menuIntent= new Intent(MenuActivity.this,AuthentificationActivity.class);
            startActivity(menuIntent);

        }

    }

}

