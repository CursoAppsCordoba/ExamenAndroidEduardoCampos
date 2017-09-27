package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class MostrarController extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    // Creamos la variable donde se guardara el activity
    Activity activity;

    final int EDITAR = 500;

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

        listView.setOnItemClickListener(this);

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

        final int posicion = i;

        AlertDialog.Builder ventanaEditar = new AlertDialog.Builder(activity);

        ventanaEditar.setTitle("Editar contacto");

        ventanaEditar.setMessage("¿Desea editar el contacto?").setCancelable(false);

        ventanaEditar.setPositiveButton("Editar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {

                Intent intent = new Intent(activity, DatosActivity.class);

                intent.putExtra("contactoEditar", (Parcelable) listaContactos.get(posicion));

                intent.putExtra("posicionEditar", posicion);

                intent.putExtra("botonAccion", "Editar");

                activity.startActivityForResult(intent, EDITAR);

            }

        });

        ventanaEditar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {

            }

        });

        ventanaEditar.show();

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

                actualizarListView();

            }

        });

        ventanaBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {

            }

        });

        ventanaBorrar.show();

        return false;

    }

    // Funcion para crear Toat para informar al usuario
    public Toast mostrarToast(String s) {

        Toast toast = Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_SHORT);

        return toast;
    }

    public void actualizarListView() {

        arrayAdapter.notifyDataSetChanged();

    }

    public void actualizarContacto(Intent intent) {

        if(intent.hasExtra("contacto")){

            listaContactos.set(intent.getIntExtra("posicionEditar", 0), intent.getParcelableExtra("contacto"));

            mostrarToast("Concacto actualizado");

        }

    }
}
