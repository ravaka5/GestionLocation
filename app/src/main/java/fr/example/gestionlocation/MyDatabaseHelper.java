package fr.example.gestionlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME= "AppLocation.db";
    private static final int DATABASE_VERSION = 1 ;
    private static final String TABLE_NAME = "Location";
    private static final String COLUMN_ID = "num_loc";
    private static final String COLUMN_NAME = "nom_loc";
    private static final String COLUMN_DESIGN = "design_voiture";
    private static final String COLUMN_DAYSCOUNT = "nbr_jours";
    private static final String COLUMN_MONEY = "taux";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+ TABLE_NAME +
                        " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESIGN + " TEXT, " +
                        COLUMN_DAYSCOUNT + " INTEGER, " +
                        COLUMN_MONEY + " INTEGER);";
        db.execSQL(query);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addLocation(String name, String design, Integer nbdays, Integer money){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESIGN, design);
        cv.put(COLUMN_DAYSCOUNT, nbdays);
        cv.put(COLUMN_MONEY, money);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Echec de l'ajout",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Ajout reussi",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor =  db.rawQuery(query, null);
        }
        return cursor;
    }
}
