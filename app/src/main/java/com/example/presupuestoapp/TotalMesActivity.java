package com.example.presupuestoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TotalMesActivity extends AppCompatActivity {
    Integer numMes;
    String numMesStr;
    Double totalMes;

    private TextView totalmes_lbl;

    private Spinner spinner1;

    private boolean isFirstSelection = true;
    private Cursor cursor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalmes_layout);

        totalmes_lbl = findViewById(R.id.totalmes);
        spinner1 = findViewById(R.id.spinner_mes);

        //Hacemos la consulta a la DB

        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext()); //en lugar de "this" funcionaria tambien el getApplicationContext()
        //Sacamos el cursor con todas las transacciones almacenadas
        cursor = myDB.readAllData();
        totalmes_lbl.setText(resultado(cursor, numMes));

        //Las siguientes lineas son la configuracion del spinner

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
                // Guardar el elemento seleccionado en la variable "opcion"
                //numMesStr = parentView.getItemAtPosition(position).toString();
                numMes = parentView.getSelectedItemPosition()+1;
                //numMes = Integer.valueOf(numMesStr);

                totalmes_lbl.setText(resultado(cursor, numMes));
                Toast.makeText(getApplicationContext(), "Opción seleccionada: " + numMes +" total " + resultado(cursor, numMes), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada si no se selecciona nada
            }
        });
    }
    private String resultado(Cursor cursor, Integer num){
        //Calculamos el total
        totalMes = getTotalMes(cursor, num);
        return ""+totalMes+" €";
    }


    private Double getTotalMes(Cursor cursor, Integer numMes){
        Double totalMes = 0.0;
        //List<Transacciones> transacciones = new ArrayList<Transacciones>();
        //Transacciones tra1 = new Transacciones(0.0, "0", 0);
        //transacciones.add(tra1);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Double importe = cursor.getDouble(cursor.getColumnIndex("gastos_importe"));
                @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("gastos_descripcion"));
                @SuppressLint("Range") Integer mes = cursor.getInt(cursor.getColumnIndex("gastos_mes"));
                if(mes == numMes){
                    totalMes+=importe;
                }

                // Crear un objeto Persona con los datos y agregarlo a la lista
                //tra1 = new Transacciones(importe, descripcion, mes);
                //transacciones.add(tra1);
            } while (cursor.moveToNext());
        }
        return totalMes;
    }

    public ArrayList<String> getArraySpinner(){
            ArrayList<String> listaMesesSpinner = new ArrayList<String>();
            listaMesesSpinner.add("Enero");
            listaMesesSpinner.add("Febrero");
            listaMesesSpinner.add("Marzo");
            listaMesesSpinner.add("Abril");
            listaMesesSpinner.add("Mayo");
            listaMesesSpinner.add("Junio");
            listaMesesSpinner.add("Julio");
            listaMesesSpinner.add("Agosto");
            listaMesesSpinner.add("Septiembre");
            listaMesesSpinner.add("Octubre");
            listaMesesSpinner.add("Noviembre");
            listaMesesSpinner.add("Diciembre");

            return listaMesesSpinner;
        }





}