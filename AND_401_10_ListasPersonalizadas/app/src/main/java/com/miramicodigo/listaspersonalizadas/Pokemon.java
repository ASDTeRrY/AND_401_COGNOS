package com.miramicodigo.listaspersonalizadas;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String nombre;
    private String habilidades;
    private int imagen;

    public Pokemon() {

    }

    public Pokemon(String nombre, String habilidades, int imagen) {
        this.nombre = nombre;
        this.habilidades = habilidades;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
