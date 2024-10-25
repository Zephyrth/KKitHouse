package org.dam.Models;

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
        return "MaterialModel{" +
                "id_Material=" + id_Material +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
