package fr.euroforma.gsb.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public boolean insertData(String type, Integer quantite, String date, double montant, String libelle) {
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
}
