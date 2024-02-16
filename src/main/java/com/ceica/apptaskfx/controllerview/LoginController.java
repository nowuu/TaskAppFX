package com.ceica.apptaskfx.controllerview;

import com.ceica.apptaskfx.TaskApplication;
import com.ceica.apptaskfx.controller.TareasController;
import com.ceica.apptaskfx.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    protected Label lblMsg;
    @FXML
    protected TextField txtUsr;
    @FXML
    protected PasswordField txtPass;

    public TareasController taskController=new TareasController();

    public void onLoginAction(ActionEvent actionEvent)  {
        if (taskController.login(txtUsr.getText(),txtPass.getText())){
            String view="";
            String titleWindow;
            if(taskController.isAdmin()){
                view="admin-view.fxml";
                titleWindow="Admin";
            }else{
                view="user-view.fxml";
                titleWindow="User";



            }
            FXMLLoader loader=new FXMLLoader(TaskApplication.class.getResource(view));
            try {
                Parent root=loader.load();
                ControllerView controller =loader.getController();
                controller.setTaskController(taskController);
                Scene scene=new Scene(root);
                Stage stage=new Stage();
                stage.setTitle(titleWindow);
                stage.setScene(scene);
                stage.show();
                Node source=(Node) actionEvent.getSource();
                Stage stage1=(Stage) source.getScene().getWindow();
                stage1.close();



            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{
            lblMsg.setText("intentalo otra vez");
        }
    }
}



