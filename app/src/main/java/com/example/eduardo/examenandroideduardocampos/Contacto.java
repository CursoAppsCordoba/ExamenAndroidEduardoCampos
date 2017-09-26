package com.example.eduardo.examenandroideduardocampos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eduardo on 26/09/2017.
 */

public class Contacto implements Parcelable{

    String nombre, correo;

    Integer edad;

    public Contacto(String nombre, String correo, Integer edad) {

        this.nombre = nombre;

        this.correo = correo;

        this.edad = edad;

    }

    public String getNombre() {

        return nombre;

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public String getCorreo() {

        return correo;

    }

    public void setCorreo(String correo) {

        this.correo = correo;

    }

    public Integer getEdad() {

        return edad;

    }

    public void setEdad(Integer edad) {

        this.edad = edad;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Contacto)) return false;

        Contacto contacto = (Contacto) o;

        if (getNombre() != null ? !getNombre().equals(contacto.getNombre()) : contacto.getNombre() != null)

            return false;

        if (getCorreo() != null ? !getCorreo().equals(contacto.getCorreo()) : contacto.getCorreo() != null)

            return false;

        return getEdad() != null ? getEdad().equals(contacto.getEdad()) : contacto.getEdad() == null;

    }

    @Override
    public int hashCode() {

        int result = getNombre() != null ? getNombre().hashCode() : 0;

        result = 31 * result + (getCorreo() != null ? getCorreo().hashCode() : 0);

        result = 31 * result + (getEdad() != null ? getEdad().hashCode() : 0);

        return result;

    }

    public Contacto(Parcel in) {

        nombre = in.readString();

        correo = in.readString();

        edad = in.readInt();

    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {

        @Override
        public Contacto createFromParcel(Parcel in) {

            return new Contacto(in);

        }

        @Override
        public Contacto[] newArray(int size) {

            return new Contacto[size];

        }

    };

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nombre);

        parcel.writeString(correo);

        parcel.writeInt(edad);

    }

    @Override
    public String toString() {

        return "Nombre: " + nombre + ", " + edad + "\nCorreo: " + correo;

    }

}
