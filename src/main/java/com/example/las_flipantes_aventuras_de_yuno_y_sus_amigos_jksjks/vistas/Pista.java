package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pista extends Stage {
    ProgressBar[] pgbCorredores = new ProgressBar[5];
    Label[] lblCorredores = new Label[5];
    private GridPane gdpPista;
    private Scene escena;
    private String[] strCorredores = {"Yuno","Rafa","Sergio","Joshua","Alma"};
    private Hilo[] thrCorredores = new Hilo[5];

    public Pista(){
        crearUI();
        this.setTitle("Pista de carreras");
        this.setScene(escena);
        this.show();
    }
    public void crearUI(){
        gdpPista = new GridPane();
        for(int i =0; i < strCorredores.length; i++){
            lblCorredores[i] = new Label(strCorredores[i]);
            pgbCorredores[i] = new ProgressBar(0);
            gdpPista.add(lblCorredores[i],0,i);
            gdpPista.add(pgbCorredores[i],1,i);
            thrCorredores[i] = new Hilo(strCorredores[i]);
            thrCorredores[i].setPgbCarril(pgbCorredores[i]);
            thrCorredores[i].start();
        }
        escena = new Scene(gdpPista, 200, 200);
    }
}
