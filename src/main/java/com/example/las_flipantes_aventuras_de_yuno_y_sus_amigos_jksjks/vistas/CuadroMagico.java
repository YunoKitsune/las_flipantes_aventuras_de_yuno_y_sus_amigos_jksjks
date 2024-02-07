package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CuadroMagico extends Stage {

    private Scene escena;

    public CuadroMagico(){
        this.setTitle("Cuadro Mágico ^w^");
        this.setScene(new Scene(new Button("Da Click")));
        this.show();
    }

    private void CrearUI(){
        escena = new Scene(new Button("Da click"));
    }

}
