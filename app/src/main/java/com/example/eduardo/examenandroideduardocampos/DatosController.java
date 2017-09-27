package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class DatosController implements View.OnClickListener{
    // Creamos la variable donde se guardara el activity
    private Activity activity;

    EditText edtNombre, edtEmail, edtEdad;

    Button btnAceptar, btnVolver;
    // Variable para crear una ventana emergente
    private AlertDialog ventana;

    private int posicion;

    public DatosController(Activity activity){
        // Inicializamos el activity
        this.activity = activity;

        edtNombre = activity.findViewById(R.id.edtNombre);

        edtEmail = activity.findViewById(R.id.edtEmail);

        edtEdad = activity.findViewById(R.id.edtEdad);

        btnAceptar = activity.findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(this);

        btnVolver = activity.findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(this);
        // Recuperamos el intent de la actividad anterior
        Intent intent = activity.getIntent();
        // Asignamos el valor del texto al boton
        btnAceptar.setText(intent.getStringExtra("botonAccion"));

        if (intent.hasExtra("contactoEditar")){

            Contacto contactoEditable = intent.getParcelableExtra("contactoEditar");

            edtNombre.setText(contactoEditable.nombre);

            edtEmail.setText(contactoEditable.correo);

            edtEdad.setText("" + contactoEditable.edad);

            posicion = intent.getIntExtra("posicionEditar", 0);

        }

    }

    @Override
    public void onClick(View view) {
        // Creamos un switch para determinar que boton se pulsa
        switch (view.getId()){

            case R.id.btnAceptar:
                // Funcion para comprobar los campos si estan vacios
                if (!comprobacionCampos()){
                    // Creamos el contacto con los datos recogidos de los EditText
                    Contacto contacto = new Contacto(edtNombre.getText().toString(), edtEmail.getText().toString(), Integer.parseInt(edtEdad.getText().toString()));

                    Intent intent = new Intent();

                    intent.putExtra("contacto", contacto);

                    intent.putExtra("posicionEditar", posicion);

                    activity.setResult(activity.RESULT_OK, intent);

                    activity.finish();

                }

                break;

            case R.id.btnVolver:
                // Si la ventana del AlertDialog no esta creada, la creamos
                if(ventana == null){
                    // Funcion para crear el AlertDialog
                    ventana = CreateDialog();

                }

                ventana.show();

                break;

        }

    }
    // Funcion para comprobar que estan rellenos los EditText
    private boolean comprobacionCampos() {

        boolean retVal = false;

        if(edtNombre.getText().toString().equals("")){

            retVal = true;

            mostrarToast("Debe introducir un nombre").show();

        }

        if(!retVal && edtEmail.getText().toString().equals("")){

            retVal = true;

            mostrarToast("Debe introducir un correo").show();

        }

        if(!retVal && edtEdad.getText().toString().equals("")){

            retVal = true;

            mostrarToast("Debe introducir una edad").show();

        }

        return retVal;
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
    // Funcion para crear Toat para informar al usuario
    public Toast mostrarToast(String s) {

        Toast toast = Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_SHORT);

        return toast;
    }

}
