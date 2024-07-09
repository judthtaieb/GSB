package fr.euroforma.gsb.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    SQLiteDatabase db;



    public static  final String CREATE = "Create table FRAIS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPEFORFAIT TEXT,LIBELLE TEXT,QUANTITE INTEGER,DATEFRAIS TEXT,MONTANT REAL,DATESAISIE DATETIME_DEFAULT_CURRENT_TIMESTAMP)";

    public BDDHelper(Context context){
        super(context,DB_NAME,null,1);//permet d'accéder aux membres de la classe mere BDDHelper


    }


    @Override
    //creation de la BDD
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);

    }

    @Override
    //mise a jour de l application
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  BDDHelper open()throws SQLException{
        db = this.getWritableDatabase();
        return this;
    }

    public boolean  insertData(String type, Integer quantite, String date, Float montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        db = this.getWritableDatabase();
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

    public Cursor ViewData(String inputText) throws SQLException {
        db = this.getReadableDatabase();
        Cursor mycursor = null;
        if (inputText == null || inputText.length() == 0) {
            mycursor = db.query(DB_TABLE, new String[]{"rowid _id", LIBELLE,
                            ID, DATEFRAIS,DATESAISIE ,MONTANT, QUANTITE},
                    null, null, null, null, null);
        }else{
            mycursor = db.query(DB_TABLE, new String[]{"rowid _id",LIBELLE,
                            ID, DATEFRAIS,DATESAISIE,MONTANT, QUANTITE},
                    DATEFRAIS + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mycursor != null) {
            mycursor.moveToFirst();
        }
        return mycursor;

    }
 // recuperer details des frais
 public ArrayList<HashMap<String, String>> GetFrais() {
     SQLiteDatabase db = this.getWritableDatabase();
     ArrayList<HashMap<String, String>> fraisList = new ArrayList<>();
     String query = "SELECT LIBELLE, MONTANT ,DATEFRAIS , DATESAISIE FROM FRAIS" ;
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

/**public Cursor viewData() {
      db = this.getWritableDatabase();
     Cursor cursor = db.query(DB_TABLE, new String[]{"rowid _id" ,ID,CP,VILLE,LIBELLE
                     , DATEFRAIS, DATESAISIE, MONTANT, QUANTITE},
             null, null, null, null, null,null,null);
     // String myQuery = "SELECT * FROM  "+DB_TABLE;
     //Cursor cursor = db.rawQuery(myQuery,null);
     if (cursor != null) {
         cursor.moveToFirst();
     }
     return cursor;

 }
**/



    public void deleteData(int parseInt) {
    }

    public int getTotalFrais() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) as compte FROM " + DB_TABLE;
        Cursor cursor= db.rawQuery(countQuery, null);
        int compte=0;

        if (cursor != null) {
            cursor.moveToFirst();
            compte = cursor.getInt(0);
            cursor.close();
        }

        return compte;
    }
    public float getMontantTotalFrais() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT SUM (MONTANT) as totalmontant FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);
        float totalM=0;


        if (cursor != null) {
            cursor.moveToFirst();
            totalM = cursor.getFloat(0);
            cursor.close();
        }


        return totalM;
    }
    public boolean verifierFrais(String inputDate , String inputType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String typeForfaitBD = "SELECT * FROM " + DB_TABLE + " GROUP BY " + TYPEFORFAIT + "='" + inputType +  "' HAVING " + DATEFRAIS + "='" + inputDate + "'";

        Cursor cursor = db.rawQuery(typeForfaitBD , null);




        return cursor != null;}
}



