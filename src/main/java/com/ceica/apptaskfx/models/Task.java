package com.ceica.apptaskfx.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends ModeloBase{

private int idTarea;
    private String title;

    private String description;
    private Date dateTime;
    private Date deadline;

     private boolean status;
     private User user;

    public Task() {
    }

    public Task(int idTarea, String title, String description,
                Date dateTime, Date deadline,
                boolean status, User user) {
        this.idTarea = idTarea;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.deadline = deadline;
        this.status = status;
        this.user = user;
    }


    // Getter y Setter para idTarea
    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    // Getter y Setter para title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter y Setter para description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter y Setter para dateTime
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    // Getter y Setter para deadline
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    // Getter y Setter para status
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Getter y Setter para user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected String getNombreTabla() {
        return "task";
    }


    protected Object createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    public List<Task> getAllByUser(int idUser) {
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task();
        Connection conn = task1.getConnection();
        String sql = "SELECT idtask,title,T0.descripcion,datetime,deadline,status,\n" +
                "T1.iduser,username,T2.id,T2.descripcion AS rol \n" +
                "from task T0 \n" +
                "left join user T1 on T0.iduser=T1.iduser\n" +
                "left join rol T2 on T1.rol=T2.id where T1.iduser=?";
        try {

           Task task=new Task();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idUser);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                task.idTarea = resultSet.getInt("idtask");
                task.title = resultSet.getString("title");
                task.description = resultSet.getString("descripcion");
                task.dateTime = resultSet.getDate("datetime");
                task.deadline = resultSet.getDate("deadline");
                task.status = resultSet.getBoolean("status");
                User user1 = new User();
                user1.setIdUser(resultSet.getInt("iduser"));
                user1.setUsername(resultSet.getString("username"));
                Rol rol = new Rol();
                rol.setIdRol(resultSet.getInt("id"));
                rol.setDescripcion(resultSet.getString("descripcion"));
                user1.setRol(rol);
                task.setUser(user1);
                taskList.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    @Override
    public String toString() {
        return "Task{" +
                "idTarea=" + idTarea +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", deadline=" + deadline +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task();
        Connection conn = task1.getConnection();
        String sql = "SELECT idtask,title,T0.descripcion,datetime,deadline,status,\n" +
                "T1.iduser,username,T2.id,T2.descripcion AS rol \n" +
                "from task T0 \n" +
                "left join user T1 on T0.iduser=T1.iduser\n" +
                "left join rol T2 on T1.rol=T2.id ";
        try {
            Task task = new Task();
            Statement statement= conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            taskList=readResulSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }
    private List<Task> readResulSet(ResultSet resultSet) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.idTarea = resultSet.getInt("idtask");
            task.title = resultSet.getString("title");
            task.description = resultSet.getString("description");
            task.dateTime = resultSet.getDate("create_date");
            task.deadline = resultSet.getDate("deadline");
            task.status = resultSet.getBoolean("status");
            User user = new User();
            user.setIdUser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            Rol rol = new Rol();
            rol.setIdRol(resultSet.getInt("idrol"));
            rol.setDescripcion(resultSet.getString("rol"));
            user.setRol(rol);
            task.user = user;
            taskList.add(task);
        }
        return taskList;
    }
}




