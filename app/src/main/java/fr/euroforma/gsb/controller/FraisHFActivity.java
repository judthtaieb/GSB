package fr.euroforma.gsb.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import fr.euroforma.gsb.R;

public class FraisHFActivity extends AppCompatActivity {

    EditText libelle;
    EditText montant;
    EditText maDate;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);

    BDDHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hfactivity);


        libelle = findViewById(R.id.libelle);
        montant = findViewById(R.id.montant);
        maDate=findViewById(R.id.maDate);

        database=new BDDHelper(this);
        database.open();

    }

    public void clickAjouterHF(View v){


        if (libelle.getText().toString().trim().length() == 0|| maDate.getText().toString().trim().length()==0) {
            //teste si le champ quantite est renseigné ou si le champ libelle n'est pas vide et si la date est renseignée
            afficheFHF("Erreur! Vous n'avez pas rempli tous les champs!");
            return;
        } else if (maDate.getText().toString().trim().length()>10 || maDate.getText().toString().trim().length()<8 ) {
            //test sur la validité du champ date
            afficheFHF("Erreur! Date invalide");
            return;

        } else {

            String tf1= libelle.getText().toString();
            String d1 = maDate.getText().toString();
            Float m1 = Float.parseFloat(montant.getText().toString());
            Integer q1 = 1;


            if(database.insertData(tf1,q1,d1,m1,tf1)){
                afficheFHF("Valeur ajoutée avec succès.Montant="+m1);
                return;
            }
        }
        afficheFHF(libelle.getText().toString()+montant.getText().toString());
    }

    public void afficheFHF(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void clickAjouterFHF(View v) {
        afficheFHF(libelle.getText().toString()+" "+montant.getText().toString());
    }

    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(FraisHFActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }

    public void retourMenu(View view) {
        Intent retourIntent= new Intent(FraisHFActivity.this, MenuActivity.class);
        startActivity(retourIntent);
    }



}





