package com.example.eduardo.examenandroideduardocampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatosActivity extends AppCompatActivity {
    // Creo un controlador donde ira todas las declaraciones
    DatosController datosController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datos);
        // Inicializo el controlador
        datosController = new DatosController(this);

    }

}
