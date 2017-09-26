package com.example.eduardo.examenandroideduardocampos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // Creo un controlador donde ira todas las declaraciones
    MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Inicializo el controlador
        mainController = new MainController(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // Si al volver al activity vuelvo desde añadir contacto
        if(requestCode == mainController.ANADIR){
            // Si la vuelta al activity ha sido mediante Aceptar
            if(resultCode == RESULT_OK){
                // Funcion para dar el alta
                mainController.alta(data);

            }
        }
        // Si al volver al activity vuelvo desde borrar contacto
        if(requestCode == mainController.BUSCAR) {
            // Si la vuelta al activity ha sido mediante Aceptar
            if(resultCode == RESULT_OK){
                // Funcion para dar de baja
                mainController.baja(data);

            }

        }
        // Si al volver al activity vuelvo desde mostrar contacto
        if (requestCode == mainController.TODOS){
            // Si la vuelta al activity ha sido mediante Aceptar
            if (resultCode == RESULT_OK){
                mainController.actualizarContactos(data);
            }
        }
        // Funcion para cambiar el valor del TextView que muestra el total de contactos
        mainController.actualizarLista();

    }

}
