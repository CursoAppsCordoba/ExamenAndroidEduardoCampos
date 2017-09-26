package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class MainController implements View.OnClickListener {

    public static final Integer ANADIR = 100, BUSCAR = 200, TODOS = 300;

    private Activity activity;

    private TextView txtTotal;

    private Set<Contacto> listaContactos = null;

    public MainController (Activity activity){

        this.activity = activity;

        txtTotal = activity.findViewById(R.id.txtTotal);

        Button btnAnadir = activity.findViewById(R.id.btnAnadir);

        btnAnadir.setOnClickListener(this);

        Button btnBorrar = activity.findViewById(R.id.btnBorrar);

        btnBorrar.setOnClickListener(this);

        Button btnTodos = activity.findViewById(R.id.btnTodos);

        btnTodos.setOnClickListener(this);

        actualizarLista();

    }

    @Override
    public void onClick(View view) {

        Intent intent = null;

        Integer envio = 0;

        boolean permiso = false;

        Toast toast = null;

        switch (view.getId()){

            case R.id.btnAnadir:

                if (listaContactos == null) {

                    listaContactos = new HashSet<>();

                }

                intent = new Intent(this.activity, DatosActivity.class);

                intent.putExtra("botonAccion", "Añadir");

                envio = ANADIR;

                break;

            case R.id.btnBorrar:

                if (listaContactos == null || listaContactos.size() == 0){

                    toast = mostrarToast("No hay contactos que borrar");

                    permiso = true;

                } else {

                    intent = new Intent(this.activity, DatosActivity.class);

                    intent.putExtra("botonAccion", "Borrar");

                    envio = BUSCAR;

                }

                break;

            case R.id.btnTodos:

                if (listaContactos == null || listaContactos.size() == 0){

                    toast = mostrarToast("No hay contactos que mostrar");

                    permiso = true;

                } else {

                    intent = new Intent(this.activity, MostrarActivity.class);

                    intent.putExtra("listaContactos", new ArrayList<>(listaContactos));

                    envio = TODOS;

                }

                break;

        }

        if (!permiso){

            activity.startActivityForResult(intent, envio);

        } else {

            toast.show();

        }

    }


    public void alta(Intent intent) {

        Toast toast;

        if (intent.hasExtra("contacto")){

            if(listaContactos.add((Contacto) intent.getParcelableExtra("contacto"))){

                toast = mostrarToast("Contacto añadido satisfactoriamente");


            } else {

                toast = mostrarToast("Ya esta introducido el contacto");

            }

            toast.show();

        }

    }

    public void baja(Intent intent) {

        Toast toast;

        if (intent.hasExtra("contacto")){

            if(listaContactos.remove(intent.getParcelableExtra("contacto"))){

                toast = mostrarToast("Contacto borrado satisfactoriamente");


            } else {

                toast = mostrarToast("Contacto no encontrado / creado");

            }

            toast.show();

        }

    }

    public void actualizarLista() {

        txtTotal.setText((listaContactos == null) ? "Total de contactos: 0" : "Total de contactos: " + listaContactos.size());

    }

    public Toast mostrarToast(String s) {

        Toast toast = Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_SHORT);

        return toast;
    }

}
