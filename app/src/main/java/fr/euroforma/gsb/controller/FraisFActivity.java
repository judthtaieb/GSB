package fr.euroforma.gsb.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import fr.euroforma.gsb.R;

public class FraisFActivity<editable> extends AppCompatActivity {

    EditText quantite;
    Spinner typeForfait;
    Button btnAjouter;
    EditText mDate;
    TextView montant;
    String[] Valeurs;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);

    BDDHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_factivity);


        quantite = findViewById(R.id.quantite);
        typeForfait = findViewById(R.id.typeForfait);
        btnAjouter = findViewById(R.id.btnAjouter);
        mDate = findViewById(R.id.dateDepense);
        montant = findViewById(R.id.txtMontant);
        Valeurs = getResources().getStringArray(R.array.valeursForfait);

        database=new BDDHelper(this);
        database.open();


        quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable e) {
                Integer q = Integer.parseInt(String.valueOf("0" + quantite.getText()));
                String f = typeForfait.getSelectedItem().toString();
                int posForfait = typeForfait.getSelectedItemPosition();
                Float m = q * Float.parseFloat(Valeurs[posForfait]);

                montant.setText(m.toString());

            }
        });
    }


   public void afficher(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void clickAjouter(View v) {
        if (quantite.getText().toString().trim().length() == 0 || typeForfait.getSelectedItem().toString().length() == 0
                || mDate.getText().toString().trim().length() == 0) {
            //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
            // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
            afficher("Erreur!Vous n'avez pas rempli tous les champs!");
            return;
        } else if (mDate.getText().toString().trim().length()>10 || mDate.getText().toString().trim().length()<8) {
            //test sur la validité du champ date
            afficher("Erreur! Date invalide");

            return;
        } else if (Integer.parseInt(quantite.getText().toString()) < 1) { //teste si la quantite est au moins 1
            afficher("Erreur! Quantité invalide");
            return;
        } else {

            String tf1 = typeForfait.getSelectedItem().toString();
            Integer q1 = Integer.parseInt(quantite.getText().toString());
            String d1 = mDate.getText().toString();
            Float m1 = Float.parseFloat(montant.getText().toString());


            if (database. insertData(tf1, q1, d1, m1, tf1)) {
                afficher("Valeur ajoutée avec succès.Montant=" + m1);
                return;
            }
            afficher(quantite.getText().toString() + typeForfait.getSelectedItem().toString());
        }
    }
    public void retourMenu(View view) {
        Intent retourIntent= new Intent(FraisFActivity.this, MenuActivity.class);
        startActivity(retourIntent);
    }
    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(FraisFActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }

}