package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Eduardo on 26/09/2017.
 */

class MostrarController extends Activity {
    // Creamos la variable donde se guardara el activity
    Activity activity;

    private ListView listView;

    private ArrayAdapter arrayAdapter;

    AlertDialog ventana;

    public MostrarController(Activity activity) {
        // Inicializamos el activity
        this.activity = activity;

        Intent intent = activity.getIntent();

        listView = activity.findViewById(R.id.lstContactos);
        // Creamos un adaptador que recupera los datos del anterior activity
        arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, (ArrayList) intent.getSerializableExtra("listaContactos"));
        // Se lo añadimos al ListView
        listView.setAdapter(arrayAdapter);

        Button btnVolver = activity.findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(ventana == null){
                    // Funcion para crear el AlertDialog
                    ventana = CreateDialog();

                }

                ventana.show();

            }

        });

    }

    // Funcion para crear el AlertDialog
    private AlertDialog CreateDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("¿Desea volver?");

        builder.setMessage("No se guardaran los cambios").setCancelable(false);

        builder.setPositiveButton("Volver", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                activity.finish();

            }

        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });

        return builder.create();

    }

}
