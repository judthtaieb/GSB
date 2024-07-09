package fr.euroforma.gsb;

import static org.junit.Assert.assertEquals;
import static fr.euroforma.gsb.controller.BDDHelper.DB_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import fr.euroforma.gsb.controller.BDDHelper;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GSBUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void insertData(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        BDDHelper database=new BDDHelper(context);
        database.insertData("KM" ,3, "23/05/2021", 25F,"test");
        Cursor C=database.ViewData("23/05/2021");
        assertEquals("test",C.getString(C.getColumnIndex("libelle")));
    }
    /*@Test
    public void testGetMontantTotalFrais() throws Exception {
       Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        BDDHelper db =new BDDHelper(context);
        assertNotNull(db.getMontantTotalFrais());
    }*/




        @Test
        public void testGetMontantTotalFrais() {
            // Créer une instance de BDDHelper avec le contexte d'instrumentation
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
            BDDHelper db = new BDDHelper(context);

            // Insérer des données de test dans la base de données
            insertTestData(db);

            // Appeler la méthode getMontantTotalFrais() et obtenir le montant total
            float montantTotal = db.getMontantTotalFrais();

            // Vérifier si le montant total est correct (selon vos données de test)
            float expectedMontantTotal = 100.0f; // Montant total attendu
            Assert.assertEquals(expectedMontantTotal, montantTotal, 0.01f); // Précision de 0.01f pour la comparaison
        }

        // Méthode utilitaire pour insérer des données de test dans la base de données
        private void insertTestData(BDDHelper db) {
            // Insérer des frais de test dans la table (à adapter selon votre structure de table)
            SQLiteDatabase writableDb = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MONTANT", 50.0f);
            writableDb.insert(DB_TABLE, null, values);
            values.put("MONTANT", 50.0f);
            writableDb.insert(DB_TABLE, null, values);
            writableDb.close();
        }
    }
