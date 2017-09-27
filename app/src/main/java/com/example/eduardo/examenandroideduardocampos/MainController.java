package com.example.eduardo.examenandroideduardocampos;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class MainController implements View.OnClickListener {
    // Variables estaticas que sirven para mandar Intent
    static final Integer ANADIR = 100, BUSCAR = 200, TODOS = 300;
    // Creamos la variable donde se guardara el activity
    private Activity activity;

    private TextView txtTotal;
    // Variable donde guardamos los contactos
    private Set<Contacto> listaContactos = null;

    public MainController (Activity activity){
        // Inicializamos el activity
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
        // Creamos un Intent para ir a otra activity
        Intent intent = null;
        // Variable que contendra los valores static
        Integer envio = 0;
        // Bandera que dejara mandar el Intent si se cumplen las condiciones
        boolean permiso = false;
        // Mensaje de aviso para comunicarse con el usuario
        Toast toast = null;
        // Creamos un switch para determinar que boton se pulsa
        switch (view.getId()){

            case R.id.btnAnadir:
                // Si la lista no se ha creado, se inicializa
                if (listaContactos == null) {

                    listaContactos = new HashSet<>();

                }
                // Asignamos los valores al Intent
                intent = new Intent(this.activity, DatosActivity.class);
                // Añadimos el valor del boton que sera el Aceptar segun el caso
                intent.putExtra("botonAccion", "Añadir");
                // Asigamos valor a la variable para controlar la vuelta
                envio = ANADIR;

                break;

            case R.id.btnBorrar:
                // Comprobamos si existen contactos para poder borrar
                if (listaContactos == null || listaContactos.size() == 0){

                    toast = mostrarToast("No hay contactos que borrar");

                    permiso = true;

                } else {
                    // Asignamos los valores al Intent
                    intent = new Intent(this.activity, DatosActivity.class);
                    // Añadimos el valor del boton que sera el Aceptar segun el caso
                    intent.putExtra("botonAccion", "Borrar");
                    // Asigamos valor a la variable para controlar la vuelta
                    envio = BUSCAR;

                }

                break;

            case R.id.btnTodos:
                // Comprobamos si existen contactos para poder mostrar
                if (listaContactos == null || listaContactos.size() == 0){

                    toast = mostrarToast("No hay contactos que mostrar");

                    permiso = true;

                } else {
                    // Asignamos los valores al Intent
                    intent = new Intent(this.activity, MostrarActivity.class);
                    // Convertimos el HashSet en una List para ordenarlo
                    List <Contacto> contactosOrdenados = new ArrayList(listaContactos);
                    // Se añade la lista para mandarla al siguiente activity
                    intent.putExtra("listaContactos", (Serializable) contactosOrdenados);
                    // Asigamos valor a la variable para controlar la vuelta
                    envio = TODOS;

                }

                break;

        }
        // Si no hay ningun error, iremos a otro activity; de lo contrario mostrar el mensaje
        if (!permiso){

            activity.startActivityForResult(intent, envio);

        } else {

            toast.show();

        }

    }
    // Funcion para dar de alta en el HashSet
    public void alta(Intent intent) {

        Toast toast;
        // Si el intent contiene un extra que se llame contacto, lo guardara si no se repite
        if (intent.hasExtra("contacto")){

            if(listaContactos.add((Contacto) intent.getParcelableExtra("contacto"))){

                toast = mostrarToast("Contacto añadido satisfactoriamente");


            } else {

                toast = mostrarToast("Ya esta introducido el contacto");

            }

            toast.show();

        }

    }
    // Funcion para dar de baja en el HashSet
    public void baja(Intent intent) {

        Toast toast;
        // Si el intent contiene un extra que se llame contacto, lo borrara si esta creado
        if (intent.hasExtra("contacto")){

            if(listaContactos.remove(intent.getParcelableExtra("contacto"))){

                toast = mostrarToast("Contacto borrado satisfactoriamente");


            } else {

                toast = mostrarToast("Contacto no encontrado / creado");

            }

            toast.show();

        }

    }
    // Funcion para cambiar el valor del TextView que muestra el total de contactos
    public void actualizarLista() {

        txtTotal.setText((listaContactos == null) ? "Total de contactos: 0" : "Total de contactos: " + listaContactos.size());

    }
    // Funcion para crear Toat para informar al usuario
    public Toast mostrarToast(String s) {

        Toast toast = Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_SHORT);

        return toast;
    }
    // Funcion que recoge el ArrayList y lo convierte en HashSet
    public void actualizarContactos(Intent intent) {

        if (intent.hasExtra("listado")){

            listaContactos = new HashSet<>((ArrayList)intent.getSerializableExtra("listado"));

        }

    }

}
