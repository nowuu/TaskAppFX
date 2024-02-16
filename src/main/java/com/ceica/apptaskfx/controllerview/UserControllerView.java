package com.ceica.apptaskfx.controllerview;

import com.ceica.apptaskfx.controller.TareasController;

public class UserControllerView extends ControllerView{
    private TareasController TaskController;

    @Override
    public void setTaskController(TareasController taskController) {
        this.TaskController=taskController;

    }

    @Override
    public void cargaInicial() {

    }
}
