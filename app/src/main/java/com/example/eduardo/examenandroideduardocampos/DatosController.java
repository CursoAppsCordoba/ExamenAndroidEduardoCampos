package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class DatosController implements View.OnClickListener{

    private Activity activity;

    EditText edtNombre, edtEmail, edtEdad;

    Button btnAceptar, btnVolver;

    private AlertDialog ventana;

    MainController mainController;

    public DatosController(Activity activity){

        this.activity = activity;

        edtNombre = activity.findViewById(R.id.edtNombre);

        edtEmail = activity.findViewById(R.id.edtEmail);

        edtEdad = activity.findViewById(R.id.edtEdad);

        btnAceptar = activity.findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(this);

        btnVolver = activity.findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(this);

        Intent intent = activity.getIntent();

        btnAceptar.setText(intent.getStringExtra("botonAccion"));

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnAceptar:

                if (!comprobacionCampos()){

                    Contacto contacto = new Contacto(edtNombre.getText().toString(), edtEmail.getText().toString(), Integer.parseInt(edtEdad.getText().toString()));

                    if (contacto != null){

                        Intent intent = new Intent();

                        intent.putExtra("contacto", contacto);

                        activity.setResult(activity.RESULT_OK, intent);

                        activity.finish();

                    }

                }

                break;

            case R.id.btnVolver:

                if(ventana == null){

                    ventana = CreateDialog();

                }

                ventana.show();

                break;

        }

    }

    private boolean comprobacionCampos() {

        boolean retVal = false;

        if(edtNombre.getText().toString().equals("")){

            retVal = true;

            mainController.mostrarToast("Debe introducir un nombre");

        }

        if(!retVal && edtEmail.getText().toString().equals("")){

            retVal = true;

            mainController.mostrarToast("Debe introducir un correo");

        }

        if(!retVal && edtEdad.getText().toString().equals("")){

            retVal = true;

            mainController.mostrarToast("Debe introducir una edad");

        }

        return retVal;
    }

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
