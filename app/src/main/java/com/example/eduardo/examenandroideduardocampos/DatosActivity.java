package com.example.eduardo.examenandroideduardocampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatosActivity extends AppCompatActivity {

    DatosController datosController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datos);

        datosController = new DatosController(this);

    }

}
