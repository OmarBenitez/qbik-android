package com.app.qbik.qbik.models;

/**
 * Created by Omar on 28/11/2014.
 */
public class Publicacion {

    public String idAsStr;

    public String titulo;

    public String banner;

    public String contenido;

    public Usuario usuario;

    public Publicacion(String s, String s1, String s2) {
        this.titulo = s;
        this.contenido = s1;
        this.banner = s2;
    }
}
