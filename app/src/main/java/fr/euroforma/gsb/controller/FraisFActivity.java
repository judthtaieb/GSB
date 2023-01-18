package fr.euroforma.gsb.controller;

import androidx.appcompat.app.AppCompatActivity;

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
                if(database.insertData(typeForfait.toString(),q,mDate.toString(),m,f)){
                    afficher("Valeur ajoutée avec succès.Montant="+m);
                    return;
                }
                montant.setText(m.toString());

            }
        });
    }

    public void setBtnAjouter(View v){
        quantite.getText();
        typeForfait.getSelectedItem();
    }
   public void afficher(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void clickAjouter(View v){
        afficher(quantite.getText().toString()+typeForfait.getSelectedItem().toString());
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