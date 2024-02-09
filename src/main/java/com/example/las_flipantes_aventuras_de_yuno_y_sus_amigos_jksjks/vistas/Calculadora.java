package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {
    private Scene escena;
    private VBox vContenedor;
    private GridPane gdpTeclado;
    private TextField txtPantalla;
    private Button[][] artBotones = new Button[4][4];
    private char[] arEtiquetas = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};

    public Calculadora(){
        crearUI();
        this.setTitle("Mi SEGUNDA calculadora ^w^(?)");
        this.setScene(escena);
        this.show();
    }

    private void crearUI(){
        txtPantalla = new TextField("0");
        gdpTeclado = new GridPane();
        crearTeclado();
        vContenedor = new VBox(txtPantalla,gdpTeclado);
        vContenedor.setSpacing(10);
        escena = new Scene(vContenedor, 200, 200);
    }

    private void crearTeclado(){
        int pos=0;
        for(int i=0; i<4 ; i++){
            for(int j=0; j<4; j++){
                artBotones[i][j] = new Button(arEtiquetas[pos]+"");
                artBotones[i][j].setPrefSize(50,50);
                int finalPos = pos;
                artBotones[i][j].setOnAction(event -> setValue(arEtiquetas[finalPos]));;
                gdpTeclado.add(artBotones[i][j],i,j);
                pos++;
            }
        }
    }

    private void setValue(char simbolo){
        txtPantalla.appendText(simbolo+"");
    }
}
