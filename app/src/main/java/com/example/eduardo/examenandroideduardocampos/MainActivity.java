package com.example.eduardo.examenandroideduardocampos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainController = new MainController(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == mainController.ANADIR){

            if(resultCode == RESULT_OK){

                mainController.alta(data);

            }
        }

        if(requestCode == mainController.BUSCAR) {

            if(resultCode == RESULT_OK){

                mainController.baja(data);

            }

        }

        mainController.actualizarLista();

    }
}
