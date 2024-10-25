package org.dam.Models;

public class MarcaModel {
    private int id_Marca;
    private String nombre;

    public MarcaModel() {
    }

    public MarcaModel(int id_Marca, String nombre) {
        this.id_Marca = id_Marca;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "MarcaModel{" +
                "id_Marca=" + id_Marca +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
