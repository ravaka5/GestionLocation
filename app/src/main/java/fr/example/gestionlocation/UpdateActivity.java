package fr.example.gestionlocation;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, design_input, nbrdays_input,money_input;
    Button UpdateButton, DeleteButton;
    String id,name,design,nbrdays,money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name_input = findViewById(R.id.name_input2);
        design_input = findViewById(R.id.design_input2);
        nbrdays_input = findViewById(R.id.nbrdays_input2);
        money_input = findViewById(R.id.money_input2);
        UpdateButton = findViewById(R.id.UpdateButton);
        DeleteButton = findViewById(R.id.DeleteButton);
        getAndSetIntentData();

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name=name_input.getText().toString().trim();
                nbrdays=nbrdays_input.getText().toString().trim();
                money=money_input.getText().toString().trim();
                design=design_input.getText().toString().trim();
                myDB.updateData(id,name,design,nbrdays,money);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("design") &&
        getIntent().hasExtra("nbrdays") && getIntent().hasExtra("money")){
            //get data
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            design=getIntent().getStringExtra("design");
            nbrdays=getIntent().getStringExtra("nbrdays");
            money=getIntent().getStringExtra("money");

            //set data
            name_input.setText(name);
            design_input.setText(design);
            nbrdays_input.setText(nbrdays);
            money_input.setText(money);


        }else {
            Toast.makeText(this, "Pas de donnees", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer"+ name +" ?");
        builder.setMessage("Etes vous surs de vouloir supprimer la location de "+name+ " ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}