package com.ceica.apptaskfx.controllerview;

import com.ceica.apptaskfx.TaskApplication;
import com.ceica.apptaskfx.controller.TareasController;
import com.ceica.apptaskfx.models.Rol;
import com.ceica.apptaskfx.models.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.List;

public class AdminControllerView extends ControllerView{
    private TareasController TaskController;



@FXML
    protected TableView<User> tblUser;
    @FXML
    protected TableColumn<User,Integer> idColumn;
    @FXML
    protected TableColumn<User,String> userNameColumn;
    @FXML
    protected TableColumn<User,String> rolColumn;
    @FXML
    protected TextField txtUserName;
    @FXML
    protected PasswordField txtPassword;
    @FXML
    protected PasswordField txtrePassword;
    @FXML
    protected ComboBox<Rol> comboRol;
    @FXML
    protected Label lblMsg;
    @FXML
    protected Button btnAdd;
    @FXML
    protected Button btnUpdate;

    private ObservableList<User> observableList= FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIdUser()));
        userNameColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getUsername()));
        rolColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getRol().getDescripcion()));
        comboRol.setConverter(new StringConverter<Rol>() {
            @Override
            public String toString(Rol rol) {
                return rol.getDescripcion();
            }

            @Override
            public Rol fromString(String s) {
                return null;
            }
        });
    }


    public AdminControllerView() {


    }

    @Override
    public void cargaInicial() {
        List<User> userList=TareasController.getAllUser();
        observableList.addAll(userList);
        tblUser.setItems(observableList);
        List<Rol> rolList=TareasController.getAllRol();
        comboRol.getItems().addAll(rolList);
    }

    public void btnAddUser(ActionEvent actionEvent) {
        if(txtPassword.getText().equals(txtrePassword.getText())){
            TareasController.newUsuario(txtUserName.getText(),txtPassword.getText(),comboRol.getSelectionModel().getSelectedItem().getIdRol());
            List<User> userList=TareasController.getAllUser();
            observableList.clear();
            tblUser.refresh();
            observableList.addAll(userList);
            tblUser.refresh();
        }else{
            lblMsg.setText("Password must be equals");
        }
    }

    public void btnUpdateUser(ActionEvent actionEvent) {
        if(txtPassword.getText().equals(txtrePassword.getText())){
            User user=tblUser.getSelectionModel().getSelectedItem();
            user.setPassword(txtPassword.getText());
            user.setRol(comboRol.getSelectionModel().getSelectedItem());
            taskController.updateUser(user);
            List<User> userList=taskController.getAllUser();
            observableList.clear();
            //tblUser.refresh();
            observableList.addAll(userList);
            tblUser.refresh();

        }else{
            lblMsg.setText("Passwords must to be equals");
        }
        //taskController.updateUser();
        btnAdd.setVisible(true);
        btnUpdate.setVisible(false);
    }
}




