package com.example.presupuestoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{ //Todos los botones se dirigirán a este view OnClickListener

    RecyclerView recyclerView;
    private Button objetivos,totalMes, mesactual, irTransaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);


        /*
        Creo los botones, les asigno un boton del layout y debajo un listener,
        usamos el setOnClickListener con el implement "view.onClickListener" porque vamos a usar muchos botones.
         */
        objetivos = findViewById(R.id.objetivos);
        objetivos.setOnClickListener(this);

        totalMes = findViewById(R.id.totalMes);
        totalMes.setOnClickListener(this);

        mesactual = findViewById(R.id.mesactual);
        mesactual.setOnClickListener(this);

        irTransaccion = findViewById(R.id.irTransaccion);
        irTransaccion.setOnClickListener(this);

    }


    @Override //generado obligatoriamente porque hemos implementado el "view onclicklistener"
    public void onClick(View view) {

        if (view.getId() == R.id.objetivos) { //aqui buscamos el id del boton directamente del elemento del layout, abajo uso otra forma pero ambas son validas
            showToast("Botón objetivos presionado");
            Intent intent1 = new Intent(this, ObjetivosActivity.class );
            startActivity(intent1);
        } else if (view.getId() == R.id.mesactual) {
            Intent intent1 = new Intent(this, Mesactual.class );
            startActivity(intent1);
        } else if (view.getId() == irTransaccion.getId()) { //aqui sin embargo usamos el objeto boton que ya hemos creado en esta clase para obtener el id
           //este boton nos lleva al add_transaction_layout
            Intent intent1 = new Intent(this, AddTransaction.class );
            startActivity(intent1);
        } else if(view.getId() == R.id.totalMes){
            Intent intent1 = new Intent(this, TotalMesActivity.class );
            startActivity(intent1);
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}