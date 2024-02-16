package com.ceica.apptaskfx.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public List<Rol> getAll() {
        List<Rol> rolList=new ArrayList<>();
        Rol rol=new Rol();
        Connection conn=rol.getConnection();
        String consulta="select id,descripcion from rol";
        try {
            Statement stm=conn.createStatement();
            ResultSet resultSet=stm.executeQuery(consulta);
            while (resultSet.next()){
                Rol rol1=new Rol();
                rol1.setIdRol(resultSet.getInt("id"));
                rol1.setDescripcion(resultSet.getString("descripcion"));
                rolList.add(rol1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rolList;
    }
}

