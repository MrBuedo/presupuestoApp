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

                Boolean ok = true;

                Integer mes = 0;

                try{
                    mes = Integer.parseInt(mes_input.getText().toString().trim());
                    if(mes<1 && mes > 12){
                        mes_input.setError("El mes debe ser un numero entre el 1 y el 12");
                        ok = false;
                    }
                } catch (Exception e){
                    importe_input.setError("El mes debe ser un numero");
                    ok = false;
                }




                String desc = descripcion_input.getText().toString().trim();
                Double imp =  0.0;
                try{
                    imp = Double.parseDouble(importe_input.getText().toString().trim());
                } catch (Exception e){
                    importe_input.setError("El importe debe ser un numero");
                    ok = false;
                }


                if(desc.isEmpty()){
                    descripcion_input.setError("La descripcion no puede estar vacía");
                    ok = false;
                }

                if(ok){
                    myDB.addGasto(imp,desc,mes);

                    Toast.makeText(getApplicationContext(), "El boton funciona", Toast.LENGTH_LONG).show();
                }



            }
        });


    }

}
