package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.CuadroMagicoLogica;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.CuadroMagicoUI;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CuadroMagico extends Stage {

    private VBox vBox;
    private HBox hHud;
    private GridPane gdpCuadroMagico;
    private Scene escena;
    private TextField txt;
    private CuadroMagicoUI interfaz;
    private CuadroMagicoLogica funcion;

    public CuadroMagico(){
        crearInterfaz();
        insertarFunciones();
        this.setTitle("Cuadro Mágico ^w^");
        this.setScene(escena);
        this.show();
    }

    private void crearInterfaz(){
        interfaz = new CuadroMagicoUI();
        funcion = new CuadroMagicoLogica(interfaz);
        escena = new Scene(interfaz.getvPrincipal());
        escena.getStylesheets().add(getClass().getResource("/estilos/cuadroMagico.css").toString());

    try{funcion.mostrarArchivoEnGrid();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private void insertarFunciones(){
        interfaz.getBtnCrearYResolver().setOnAction(event ->
        {try {
                funcion.rellenarArchivo();
                funcion.mostrarArchivoEnGrid();
            } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("No se puede resolver eso");
        }
        });
    }

}
