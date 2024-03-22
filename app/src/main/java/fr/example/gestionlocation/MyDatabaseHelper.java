package fr.example.gestionlocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
