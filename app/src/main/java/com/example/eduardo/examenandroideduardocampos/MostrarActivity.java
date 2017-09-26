package com.example.eduardo.examenandroideduardocampos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MostrarActivity extends AppCompatActivity {

    MostrarController mostrarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar);

        mostrarController = new MostrarController(this);

    }

}
