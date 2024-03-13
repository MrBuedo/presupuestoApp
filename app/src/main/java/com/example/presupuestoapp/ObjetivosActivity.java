package com.example.presupuestoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjetivosActivity extends AppCompatActivity {
    Integer numMes;
    String mesStr;
    Double objetivo;

    private EditText objetivo_input;

    private Spinner spinner1;
    private boolean isFirstSelection = true;

    private Button objetivo_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objetivos_layout);

        objetivo_input = findViewById(R.id.objetivos);

        objetivo_btn = findViewById(R.id.cambiarObjetivo);

        spinner1 = findViewById(R.id.spinner_objetivo);




        List<String> meses = new ArrayList<>(Arrays.asList(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        ));

        // Crear un adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el spinner
        spinner1.setAdapter(adapter);

        // Establecer un listener para detectar la selección del usuario
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                    return;
                }
                numMes = parentView.getSelectedItemPosition()+1;
                mesStr = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Opción seleccionada: " + numMes + " " + mesStr, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {            }
        });



        objetivo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                Boolean ok = true;
                Integer mes = 0;

                String desc = "Objetivo "+mesStr;
                Double imp =  0.0;
                try{
                    imp = Double.parseDouble(objetivo_input.getText().toString().trim());
                } catch (Exception e){
                    objetivo_input.setError("El objetivo debe ser un numero");
                    ok = false;
                }
                if(ok){
                    myDB.addObjetivo(imp,desc,numMes);
                    Toast.makeText(getApplicationContext(), "Objetivo Añadido", Toast.LENGTH_LONG).show();
                }
            }
        });





    }

}