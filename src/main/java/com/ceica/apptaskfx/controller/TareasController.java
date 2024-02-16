package com.ceica.apptaskfx.controller;



import com.ceica.apptaskfx.bbdd.Conexion;
import com.ceica.apptaskfx.models.Rol;
import com.ceica.apptaskfx.models.Task;
import com.ceica.apptaskfx.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TareasController {

    public User userLogged;



    public User getUserLogged() {
        return userLogged;
    }

    public boolean login(String username, String password) {

        User user = new User();
        userLogged = user.login(username, password);
        if (userLogged != null) {
            return true;
        } else {
            return false;
        }
    }
//    public boolean login(String username, String password) {
//        Connection connection = Conexion.conectar();
//        String sql = "select T0.username,T0.iduser,T1.id,T1.descripcion from user T0 " +
//                "left join rol T1 on T0.rol=T1.id where username=? and password=?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                User user = new User();
//                user.setIdUser(resultSet.getInt("iduser"));
//                user.setUsername(resultSet.getString("username"));
//                Rol rol = new Rol();
//                rol.setIdRol(resultSet.getInt("id"));
//                rol.setDescripcion(resultSet.getString("descripcion"));
//                user.setRol(rol);
//                userLogged = user;
//                return true;
//            } else {
//                userLogged = null;
//                return false;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }


    public static boolean newUsuario(String username, String password, int rol) {

        // Crear un nuevo usuario
        User newUser = new User();

        // Agregar el nuevo usuario a la lista
        return newUser.insertar("(username,password,rol) values (?,?,?)", username, password, rol);


    }


    public boolean userExists(String username, String password) {
        Connection connection = Conexion.conectar();
        String sql = "SELECT T0.username, T0.iduser, T1.id, T1.descripcion FROM user T0 " +
                "LEFT JOIN rol T1 ON T0.rol = T1.id WHERE username=? AND password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Verificar si el conjunto de resultados tiene al menos una fila
            if (resultSet.next()) {
                // El usuario existe
                return true;
            } else {
                // El usuario no existe
                return false;
            }
        } catch (SQLException e) {
            // Manejar excepciones SQL
            e.printStackTrace(); // O manejar de otra manera
            return false;
        }
    }

    public boolean borrarUsers(int iduser) {
        User newUser = new User();
        return (newUser.borrar("iduser=?", iduser));

    }

    public boolean editPassword(String username, String password) {
        User user = new User();
        return user.actualizar("password=? where username=?", password, username);
    }

    public boolean createTask(String title, String description, LocalDate deadline) {
        Task task = new Task();
        return task.insertar("(title,descripcion,deadline,iduser) values (?,?,?,?)", title, description, deadline, userLogged.getIdUser());

    }
public boolean editStatusTask(int idtask){
        Task task=new Task();
        return task.actualizar("status=1 where idtask=?",idtask);

}

public boolean deleteTask(int idtask){
        Task task=new Task();
        return task.borrar("idtask=?",idtask);
}
    public List<Task> getAllTaskUser() {
        Task task = new Task();
        return task.getAllByUser(userLogged.getIdUser());
    }

   public List<Task> getAllTask() {
        Task task = new Task();
        return task.getAll();
   }

    public boolean isAdmin() {
        return userLogged.getRol().getIdRol()==1?true:false;
    }
    public static List<User> getAllUser() {
        User user=new User();
        return  user.getAll();
    }
    public static List<Rol> getAllRol() {
        Rol rol=new Rol();
        return rol.getAll();
    }
    public boolean updateUser(User user) {
        return user.actualizar("password=?,idrol=? where iduser=?",user.getPassword(),user.getRol().getIdRol(),user.getIdUser());
    }
}


