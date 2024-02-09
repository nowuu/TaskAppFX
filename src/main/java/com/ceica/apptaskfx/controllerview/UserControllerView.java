package com.ceica.apptaskfx.controllerview;

import com.ceica.apptaskfx.controller.TareasController;

public class UserControllerView implements IcontrollerView{
    private TareasController TaskController;

    @Override
    public void setTaskController(TareasController taskController) {
        this.TaskController=taskController;

    }
}
