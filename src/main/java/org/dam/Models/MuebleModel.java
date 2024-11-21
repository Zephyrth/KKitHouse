package org.dam.Models;

import java.time.LocalDate;

public class MuebleModel {

    private int id_Mueble, stock;
    private double precio;
    private String nombre;
    private MaterialModel materialModel;
    private MarcaModel marcaModel;
    private LocalDate date;
    private boolean is_Exterior;
    private String imagenPath;

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public MuebleModel() {

    }

    public MuebleModel(int stock, double precio, String nombre, MaterialModel materialModel, MarcaModel marcaModel, LocalDate date, boolean is_Exterior) {
        this.stock = stock;
        this.precio = precio;
        this.nombre = nombre;
        this.materialModel = materialModel;
        this.marcaModel = marcaModel;
        this.date = date;
        this.is_Exterior = is_Exterior;
    }

    public MuebleModel(int id_Mueble, int stock, double precio, String nombre, MaterialModel materialModel, MarcaModel marcaModel, LocalDate date, boolean is_Exterior) {
        this.id_Mueble = id_Mueble;
        this.stock = stock;
        this.precio = precio;
        this.nombre = nombre;
        this.materialModel = materialModel;
        this.marcaModel = marcaModel;
        this.date = date;
        this.is_Exterior = is_Exterior;
    }

    public MuebleModel(int id_Mueble, int stock, double precio, String nombre, MaterialModel materialModel, MarcaModel marcaModel, LocalDate date, boolean is_Exterior, String imagenPath) {
        this.id_Mueble = id_Mueble;
        this.stock = stock;
        this.precio = precio;
        this.nombre = nombre;
        this.materialModel = materialModel;
        this.marcaModel = marcaModel;
        this.date = date;
        this.is_Exterior = is_Exterior;
        this.imagenPath = imagenPath;
    }

    public int getId_Mueble() {
        return id_Mueble;
    }

    public void setId_Mueble(int id_Mueble) {
        this.id_Mueble = id_Mueble;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MaterialModel getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(MaterialModel materialModel) {
        this.materialModel = materialModel;
    }

    public MarcaModel getMarcaModel() {
        return marcaModel;
    }

    public void setMarcaModel(MarcaModel marcaModel) {
        this.marcaModel = marcaModel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return imagenPath;
    }

    public boolean isIs_Exterior() {
        return is_Exterior;
    }

    public void setIs_Exterior(boolean is_Exterior) {
        this.is_Exterior = is_Exterior;
    }
}
