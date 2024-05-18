package fr.example.gestionlocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AddActivity extends AppCompatActivity {

    EditText name_input, design_input,nbrdays_input,money_input;
    Button AddButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name_input = findViewById(R.id.name_input2);
        design_input = findViewById(R.id.design_input2);
        nbrdays_input = findViewById(R.id.nbrdays_input2);
        money_input = findViewById(R.id.money_input2);
        AddButton = findViewById(R.id.UpdateButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = name_input.getText().toString().trim();
                String design = design_input.getText().toString().trim();
                String nbrDaysStr = nbrdays_input.getText().toString().trim();
                String moneyStr = money_input.getText().toString().trim();

                if (name.isEmpty() || design.isEmpty() || nbrDaysStr.isEmpty() || moneyStr.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return; // Sortie de la méthode onClick car les champs sont vides
                }
                else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addLocation(name_input.getText().toString().trim(),
                            design_input.getText().toString().trim(),
                            Integer.valueOf(nbrdays_input.getText().toString().trim()),
                            Integer.valueOf(money_input.getText().toString().trim()));
                    Intent intent = new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}