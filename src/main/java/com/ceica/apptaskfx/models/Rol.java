package com.ceica.apptaskfx.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rol extends ModeloBase{

    private int idRol;

    private String descripcion;


    public Rol(int idRol, String descripcion) {
        this.idRol = idRol;
        this.descripcion = descripcion;
    }

    public Rol() {

    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "rol";
    }


    protected Object createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

}
