package org.dam.Models;

import java.util.Objects;

public class MaterialModel {
    private int id_Material;
    private String nombre;
    private double precio;

    public MaterialModel() {
    }

    public MaterialModel(int id_Material, String nombre, double precio) {
        this.id_Material = id_Material;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_Material() {
        return id_Material;
    }

    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialModel that = (MaterialModel) o;
        return id_Material == that.id_Material && Double.compare(precio, that.precio) == 0 && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Material, nombre, precio);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
