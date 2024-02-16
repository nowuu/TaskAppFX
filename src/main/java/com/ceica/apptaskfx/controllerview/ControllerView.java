package com.ceica.apptaskfx.controllerview;

import com.ceica.apptaskfx.controller.TareasController;

public abstract class ControllerView {

    protected TareasController taskController;

    public void setTaskController(TareasController taskController) {
        this.taskController=taskController;
        cargaInicial();
    }

    public abstract void cargaInicial();
}
