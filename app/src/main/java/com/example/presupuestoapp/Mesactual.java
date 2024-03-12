package com.example.presupuestoapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Mesactual extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Transacciones> dataList; //Aqui almacenaremos las transacciones como objetos cuando las saquemos de la DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesactual_layout);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDatabaseHelper myDB = new MyDatabaseHelper(Mesactual.this); //en lugar de "this" funcionaria tambien el getApplicationContext()


        //Sacamos el cursor con todas las transacciones almacenadas
        Cursor cursor = myDB.readAllData();

        //Aqui transformamos el cursor en una List de objetos transaccion
        dataList = getTransacciones(cursor);
        // Cargar datos de ejemplo
        /*
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("Elemento " + (i + 1));
        }*/

        // Configurar el adaptador
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    /*
    Este método "getTransacciones" transforma los datos del cursor que da el metodo de MyDataBaseHelper
    "readAllData()" en una Lista del objeto transacciones
     */
    private List<Transacciones> getTransacciones(Cursor cursor){
        List<Transacciones> transacciones = new ArrayList<Transacciones>();
        Transacciones tra1 = new Transacciones(0.0, "0", 0);
        transacciones.add(tra1);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Double importe = cursor.getDouble(cursor.getColumnIndex("gastos_importe"));
                @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("gastos_descripcion"));
                @SuppressLint("Range") Integer mes = cursor.getInt(cursor.getColumnIndex("gastos_mes"));


                // Crear un objeto Persona con los datos y agregarlo a la lista
                 tra1 = new Transacciones(importe, descripcion, mes);
                transacciones.add(tra1);
            } while (cursor.moveToNext());
        }
        return transacciones;
    }

    /*
    Esta clase privada "MyAdapter" es necesaria para poder visualizar las transacciones en forma de lista
    dentro de la recyclerview. Para Hacerlo utilizamos como elemento intermedio el layout "item_layout"
     */
    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<Transacciones> transacionList;

        public MyAdapter(List<Transacciones> dataList) {
            this.transacionList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Transacciones data = transacionList.get(position);

            //Este if simplemente nos sirve como una forma de añadirle una cabecera con etiquetas a la tabla
            if(position==0){
                holder.mes_lbl.setText("Mes");
                holder.descripcion_lbl.setText("Descripcion");
                holder.importe_lbl.setText("Importe");
            }else { //Aqui abajo en cambio lo que hacemos es asignar cada columna de la tabla de la DB al TextViewer donde aparecera la info en pantalla
                holder.mes_lbl.setText(data.mes.toString());
                holder.descripcion_lbl.setText(data.descripcion);
                holder.importe_lbl.setText(data.importe.toString());
            }


        }

        @Override
        public int getItemCount() {
            return transacionList.size();
        }

        /*
        Esta clase publica simplemente sirve para identificar cada TextView del layout con un objeto java
        de tipo TextView
         */
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mes_lbl, descripcion_lbl, importe_lbl;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mes_lbl = itemView.findViewById(R.id.mes_lbl);
                descripcion_lbl = itemView.findViewById(R.id.descripcion_lbl);
                importe_lbl = itemView.findViewById(R.id.importe_lbl);


            }
        }

    }

}
