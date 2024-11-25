package org.dam.Models;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarcaModel that = (MarcaModel) o;
        return id_Marca == that.id_Marca && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Marca, nombre);
    }

    @Override
    public String toString() {
        return nombre;
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
