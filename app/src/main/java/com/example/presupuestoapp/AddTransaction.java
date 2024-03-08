package com.example.presupuestoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTransaction extends AppCompatActivity {

    EditText importe_input, descripcion_input, mes_input;
    Button addGasto_button;

    RecyclerView recyclerView;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_layout);

        recyclerView = findViewById(R.id.recyclerView);
        importe_input = findViewById(R.id.importe_input);
        descripcion_input = findViewById(R.id.descripcion_input );
        mes_input = findViewById(R.id.mes_input);
        addGasto_button = findViewById(R.id.add_button);


        addGasto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddTransaction.this);
                myDB.addGasto(Integer.valueOf(importe_input.getText().toString().trim()),
                        descripcion_input.getText().toString().trim(),
                        importe_input.getText().toString().trim());
            }
        });


    }
    public void añadir(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddTransaction.this);
        myDB.addGasto(Integer.valueOf(importe_input.getText().toString().trim()),
                descripcion_input.getText().toString().trim(),
                importe_input.getText().toString().trim());
        Toast.makeText(this.context, "El boton funciona", Toast.LENGTH_SHORT).show();
        System.out.println("El boton funciona?");
    }
}
