package fr.example.gestionlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyDatabaseHelper{

    private Context context;



     public MyDatabaseHelper(@Nullable Context context) {
        this.context = context;
    }


    void addLocation(String name, String design, Integer nbrdays, Integer money){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = "http://192.168.88.250/Crud_location/create.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(context, "Succès de la requete", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getLocalizedMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("nom_loc",name);
                paramV.put("design",design);
                paramV.put("nbr_jours",String.valueOf(nbrdays));
                paramV.put("taux",String.valueOf(money));
                return paramV;
            }
        };
        queue.add(stringRequest);

    }

    public void readAllData(VolleyCursorCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = "http://192.168.88.250/Crud_location/read.php";

        String[] columns = new String[]{"num_loc", "nom_loc", "design", "nbr_jours", "taux"};
        MatrixCursor cursor = new MatrixCursor(columns);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String num_loc = jsonObject.getString("num_loc");
                                String nom_loc = jsonObject.getString("nom_loc");
                                String design = jsonObject.getString("design");
                                String nbr_jours = jsonObject.getString("nbr_jours");
                                String taux = jsonObject.getString("taux");

                                cursor.addRow(new Object[]{num_loc, nom_loc, design, nbr_jours, taux});
                            }
                            callback.onSuccess(cursor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getLocalizedMessage());
                callback.onError(error);
            }
        });
        queue.add(stringRequest);

    }


    void updateData(String row_id, String name, String design, String nbrdays, String money){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = "http://192.168.88.250/Crud_location/update.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(context, "Succès de la requete", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getLocalizedMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("num_loc",row_id);
                paramV.put("nom_loc",name);
                paramV.put("design",design);
                paramV.put("nbr_jours",nbrdays);
                paramV.put("taux",money);
                return paramV;
            }
        };
        queue.add(stringRequest);

    }

     void deleteOneRow(String row_id){
         RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
         String url = "http://192.168.88.250/Crud_location/delete.php";

         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         if (response.equals("Success")){
                             Toast.makeText(context, "Succès de la requete", Toast.LENGTH_SHORT).show();
                         }
                         else Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.e("Error",error.getLocalizedMessage());
             }
         }){
             @Nullable
             @Override
             protected Map<String, String> getParams(){
                 Map<String, String> paramV = new HashMap<>();
                 paramV.put("num_loc",row_id);
                 return paramV;
             }
         };
         queue.add(stringRequest);
     }

     void deleteAllData(){
         RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
         String url = "http://192.168.88.250/Crud_location/deleteAll.php";

         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         if (response.equals("Success")){
                             Toast.makeText(context, "Succès de la requete", Toast.LENGTH_SHORT).show();
                         }
                         else Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.e("Error",error.getLocalizedMessage());
             }
         });
         queue.add(stringRequest);
     }
}
