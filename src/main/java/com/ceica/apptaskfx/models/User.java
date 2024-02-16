package com.ceica.apptaskfx.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User extends ModeloBase{
     private int idUser;
     private String username;

     private String password;
      private Rol rol;

    public User() {
    }

    public User(int idUser, String username, String password, Rol rol) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public User(String username, String password) {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", rol=" + rol +
                '}';
    }





    @Override
    protected String getNombreTabla() {
        return "user";
    }


    protected Object createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    public User login(String username, String password) {
        User user=new User();
        Connection conn=user.getConnection();
        String sql="select iduser,username,rol.id,descripcion from " +
                "user left join rol on user.rol=rol.id " +
                "where username=? and password=?";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet resultSet=pst.executeQuery();
            if(resultSet.next()){
                user.idUser=resultSet.getInt("iduser");
                user.username=resultSet.getString("username");
                Rol rol=new Rol();
                rol.setIdRol(resultSet.getInt("id"));
                rol.setDescripcion(resultSet.getString("descripcion"));
                user.rol=rol;
                return user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<User> getAll() {
        List<User> userList=new ArrayList<>();
        User user=new User();
        Connection conn=user.getConnection();
        String consulta="SELECT u.iduser, u.username, u.rol, r.descripcion FROM user u INNER JOIN rol r ON u.rol = r.id";
        try {
            Statement stm=conn.createStatement();
            ResultSet resultSet=stm.executeQuery(consulta);
            while (resultSet.next()){
                User user1=new User();
                user1.setIdUser(resultSet.getInt("iduser"));
                user1.setUsername(resultSet.getString("username"));
                Rol rol1=new Rol();
                rol1.setIdRol(resultSet.getInt("rol"));
                rol1.setDescripcion(resultSet.getString("descripcion"));
                user1.setRol(rol1);
                userList.add(user1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}