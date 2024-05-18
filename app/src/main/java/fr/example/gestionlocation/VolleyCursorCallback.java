package fr.example.gestionlocation;

import android.database.Cursor;

public interface VolleyCursorCallback {
    void onSuccess(Cursor cursor);
    void onError(Exception e);
}
