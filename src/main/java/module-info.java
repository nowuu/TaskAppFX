module com.ceica.apptaskfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ceica.apptaskfx to javafx.fxml;
    exports com.ceica.apptaskfx;
    exports com.ceica.apptaskfx.controllerview;
    opens com.ceica.apptaskfx.controllerview to javafx.fxml;
}