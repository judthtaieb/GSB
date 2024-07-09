package fr.euroforma.gsb.controller;

import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import fr.euroforma.gsb.R;

public class SyntheseActivity extends Activity {
    private Cursor cursor;
    private ArrayAdapter<DataFrais> mArray;
    private BDDHelper db;
    private SimpleCursorAdapter dataAdapter ;
    Button btnAfficher;
    TextView txtTotal;
    TextView totalMontant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese);
        btnAfficher = findViewById(R.id.btnAfficher);

        db = new BDDHelper(this);

        db.open();
        txtTotal=findViewById(R.id.total);
        totalMontant=findViewById((R.id.totalMontant));
        displayListView();



    }
    public void clickAfficherMontant(View view){
        txtTotal.setVisibility(View.VISIBLE);
        totalMontant.setVisibility(View.VISIBLE);
        totalMontant.setText(String.valueOf(db.getMontantTotalFrais()));
    }
        private void displayListView() {

             Cursor cursor = db.ViewData(null);

            // Les colonnes que l’on veut lier
            String[] columns = new String[]{

                    BDDHelper.LIBELLE,
                    BDDHelper.MONTANT,
                    BDDHelper.DATEFRAIS,
                    BDDHelper.DATESAISIE,
                    BDDHelper.QUANTITE,
                    BDDHelper.ID

            };

            // Les éléments defnis dans le XML auxquels les données sont liées
            int[] to = new int[]{



                    R.id.libelleF,
                    R.id.montantF,
                    R.id.dateF ,
                    R.id.dateSaisieF,
                    R.id.quantite,
                    R.id.idFrais

            };
            //On crée l'adaptateur à l'aide du curseur pointant sur les données souhaitées
            // ainsi que les informations de mise en page
            dataAdapter = new SimpleCursorAdapter(
                    this, R.layout.details_frais,
                    cursor,
                    columns,
                    to,
                    0);

            ListView lv = (ListView) findViewById(R.id.liste_frais);
            // Attribuer l’adapter au ListView
            lv.setAdapter(dataAdapter);

            //Pour que l'id du frais s'affiche quand on clique dessus, et pour supprimer le frais
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
              public void onItemClick(AdapterView<?> listView, View view,
                                  int position, long id) {
        // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        //On obtient l'id du frais en cliquant sur le frais
        String myId = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
        Toast.makeText(getApplicationContext(), myId, Toast.LENGTH_SHORT).show();
        db.deleteData(parseInt(myId));


    }
});
           /** int total = db.totalFiches();
            System.out.println("Nombre total de fiches de frais: " + total);*/
            EditText myFilter = (EditText) findViewById(R.id.myFilter);
            myFilter.addTextChangedListener(new TextWatcher() {


                public void afterTextChanged(Editable s) {

        }

public void beforeTextChanged(CharSequence s, int start,
        int count, int after) {

        }

public void onTextChanged(CharSequence s, int start,
        int before, int count) {
        dataAdapter.getFilter().filter(s.toString());
        }
        });
        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
public Cursor runQuery(CharSequence constraint) {
        return db.ViewData(constraint.toString());
        }
        });

        }





public void retourMenu (View view){
            Intent retourIntent = new Intent(SyntheseActivity.this, MenuActivity.class);
            startActivity(retourIntent);
    }

}
