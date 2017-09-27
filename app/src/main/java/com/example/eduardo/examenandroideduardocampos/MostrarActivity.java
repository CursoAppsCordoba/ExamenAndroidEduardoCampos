package com.example.eduardo.examenandroideduardocampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class MostrarActivity extends AppCompatActivity {
    // Creo un controlador donde ira todas las declaraciones
    MostrarController mostrarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar);
        // Inicializo el controlador
        mostrarController = new MostrarController(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == mostrarController.EDITAR){

            if (resultCode == RESULT_OK){

                mostrarController.actualizarContacto(data);

                mostrarController.actualizarListView();
            }

        }

    }
}
