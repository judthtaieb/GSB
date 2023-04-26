package fr.euroforma.gsb.controller;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class BDDHelper extends SQLiteOpenHelper {
    public static  final  String DB_NAME ="GSB.db";
    public static final  String DB_TABLE= "FRAIS";
    //definition des champs de la table
    public static  final String ID ="ID";//identifiant
    public static  final String TYPEFORFAIT = "TYPEFORFAIT";//nuitée ,resto...
    public static final  String  LIBELLE ="LIBELLE";
    public static final String QUANTITE ="QUANTITE";
    public static final  String  DATEFRAIS ="DATEFRAIS";//date de la depense
    public static final  String MONTANT="MONTANT";//montant de la depense
    public static final  String  DATESAISIE ="DATESAISIE";//date ou la depense a ete saisie sur l application


    public static  final String CREATE = "Create table FRAIS (ID INTEGER PRIMARY KEY AUTOINCREMENT ,TYPEFORFAIT TEXT,LIBELLE TEXT,QUANTITE INTEGER,DATEFRAIS TEXT,MONTANT INTEGER,DATESAISIE DATETIME_DEFAULT_CURRENT_TIMESTAMP)";

    public BDDHelper(Context context){
        super(context,DB_NAME,null,1);//permet d'accéder aux membres de la classe mere BDDHelper


    }


    @Override
    //creation de la BDD
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE  );

    }

    @Override
    //mise a jour de l application
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  BDDHelper open()throws SQLException{
        SQLiteDatabase db = this.getWritableDatabase();
        return this;
    }

    public boolean insertData(String type, Integer quantite, String date, Float montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPEFORFAIT, type);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATEFRAIS, date);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

 /** public Cursor viewData() {
        SQLiteDatabase db = this.getWritableDatabase();


        if (inputText == null  ||  inputText.length () == 0)  {
        Cursor cursor= db.query(DB_TABLE, new String[] {ID,
                        LIBELLE, DATEFRAIS, DATESAISIE, MONTANT,QUANTITE},
                null, null, null, null, null,null);


        }else {
             Cursor cursor =db.query(true, DB_TABLE, new String[] {ID,
                            LIBELLE, DATEFRAIS, DATESAISIE, MONTANT,QUANTITE},
                    DATEFRAIS + " like '%" + inputText + "%'", null,
                    null, null, null, null,null);
        }

      /**if (cursor != null) {
        cursor.moveToFirst();}

        return cursor;

    }*/
 public Cursor viewData() {
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor cursor = db.query(DB_TABLE, new String[]{"rowid _id"  ,ID,LIBELLE
                     , DATEFRAIS, DATESAISIE, MONTANT, QUANTITE},
             null, null, null, null, null);
     // String myQuery = "SELECT * FROM  "+DB_TABLE;
     //Cursor cursor = db.rawQuery(myQuery,null);
     if (cursor != null) {
         cursor.moveToFirst();
     }
     return cursor;

 }

    // recuperer details des frais
    public ArrayList<HashMap<String, String>> GetFrais() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> fraisList = new ArrayList<>();
        String query = "SELECT LIBELLE, MONTANT ,DATEFRAIS , DATESAISIE  FROM FRAIS " ;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
        HashMap<String, String> frais = new HashMap<>();
        frais.put("LIBELLE", cursor.getString(cursor.getColumnIndex(LIBELLE)));
        frais.put("MONTANT", cursor.getString(cursor.getColumnIndex(MONTANT)));
        frais.put("DATEFRAIS", cursor.getString(cursor.getColumnIndex(DATEFRAIS)));
        frais.put("DATESAISIE", cursor.getString(cursor.getColumnIndex(DATESAISIE)));


        fraisList.add(frais);
        }
        return  fraisList;
    }


    public void deleteData(int parseInt) {
    }

}
