package com.iyari.contactos.util;

import java.io.Serializable;

/**
 * Created by CONVERGENCE.
 */
public class Contacto implements Serializable{
    private String nombre, telefono, mail, direccion ;
    private String imageurl;  //No es posible serializar objetos uri, serializable se utilizan para los fragments



    public Contacto(String nombre, String telefono, String mail, String direccion, String imageurl) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.direccion = direccion;
        this.imageurl = imageurl;

    }

    //<editor-fold desc="Getter and Setter">


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (!nombre.equals(contacto.nombre)) return false;
        if (!telefono.equals(contacto.telefono)) return false;
        if (!mail.equals(contacto.mail)) return false;
        if (!direccion.equals(contacto.direccion)) return false;
        return imageurl.equals(contacto.imageurl);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + telefono.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + imageurl.hashCode();
        return result;
    }
}


