package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Eduardo on 26/09/2017.
 */

class MostrarController extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    // Creamos la variable donde se guardara el activity
    Activity activity;

    private ListView listView;

    private ArrayAdapter arrayAdapter;

    private ArrayList listaContactos;

    AlertDialog ventana;

    public MostrarController(Activity activity) {
        // Inicializamos el activity
        this.activity = activity;

        Intent intent = activity.getIntent();

        listView = activity.findViewById(R.id.lstContactos);

        listaContactos = (ArrayList) intent.getSerializableExtra("listaContactos");
        // Ordenamos los valores introducidos en el HashSet
        Collections.sort(listaContactos);
        // Creamos un adaptador que recupera los datos del anterior activity
        arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listaContactos);
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

        listView.setOnItemLongClickListener(this);

    }

    // Funcion para crear el AlertDialog
    private AlertDialog CreateDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("¿Desea volver?");

        builder.setMessage("No se guardaran los cambios").setCancelable(false);

        builder.setPositiveButton("Volver", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent();

                intent.putExtra("listado", listaContactos);

                activity.setResult(activity.RESULT_OK, intent);

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(activity, "hola", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {

        final int posicion = i;

        AlertDialog.Builder ventanaBorrar = new AlertDialog.Builder(activity);

        ventanaBorrar.setTitle("Borrar contacto");

        ventanaBorrar.setMessage("Se borrara el siguiente contacto").setCancelable(false);

        ventanaBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                listaContactos.remove(posicion);

                arrayAdapter.notifyDataSetChanged();
            }
        });
        ventanaBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {

            }

        });

        ventanaBorrar.show();

        return false;

    }

}
