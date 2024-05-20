package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class MemoramaP extends Stage {
    public Scene escena;
    public Panel pnlPrincipal;
    public HBox hOpciones;
    public HBox hJ1, hJ2, hParesJ1, hParesJ2;
    public VBox vJugadores;
    public GridPane gridCartas;
    public Label lblNoPares, lblTimer, lblGridCartas, lblJ1, lblJ2, lblParesJ1, lblParesJ2, lblMensajeError;
    public TextField txtFldPares;
    public Button btnRevolver;
    public Button[][] btnCarta;

    public MemoramaP(){
        crearUI();
        crearYMostrarEscena();
    }
    public void crearUI(){
        crearUIPrincipal();
        crearUIOpciones();
        crearUIJuego();
        juntarUIs();
    }
    public void crearUIPrincipal(){ //Crea el espacio de la interfaz
        hOpciones = new HBox();
        pnlPrincipal = new Panel();
    }
    public void juntarUIs(){
        pnlPrincipal.setHeading(hOpciones);
        pnlPrincipal.setRight(vJugadores);
        pnlPrincipal.setCenter(lblGridCartas);
        pnlPrincipal.setPadding(new Insets(3));
    }
    public void crearUIOpciones(){
        lblNoPares = new Label("No. pares:");
       /* lblNoPares.setPrefSize(125, 30);
        lblNoPares.setFont(new Font("Arial", 22));
        lblNoPares.setAlignment(Pos.BOTTOM_CENTER);*/
        //hLblPares = new HBox(lblNoPares);

        txtFldPares = new TextField("0");
        //txtFldPares.setPrefSize(200, 30);
        //txtFldPares.setFont(new Font("Arial", 22));

        lblGridCartas = new Label();
        lblMensajeError = new Label("Escriba el numero \n" +
                "de pares y presione \n" +
                "revolver para \n" +
                "comenzar a jugar\n\n" +
                "Solo se aceptan \n" +
                "entre 3 y 16 pares");
        lblMensajeError.setFont(new Font("Arial", 20));
        lblMensajeError.setPadding(new Insets(6));
        lblMensajeError.getStyleClass().addAll("lbl-info");

        btnRevolver = new Button("Revolver");
        btnRevolver.setPrefSize(200, 30);
        //btnRevolver.setFont(new Font("Arial", 22));
        btnRevolver.setStyle("-fx-font-size: 22px;");
        btnRevolver.setOnAction( event -> crearCosas() );
        //btnRevolver.setOnAction( event -> crearGridCartas(Integer.parseInt(txtFldPares.getText())) );


        //hBtnRevolver = new HBox(btnRevolver);

        lblTimer = new Label("00:00");
        lblTimer.setPrefSize(200, 30);
        lblTimer.setFont(new Font("Arial", 22));
        //hTimer = new HBox(lblTimer);

        hOpciones.getChildren().addAll(lblNoPares, txtFldPares, btnRevolver, lblTimer);
        hOpciones.setPrefSize(1200, 70);
        hOpciones.setSpacing(20);
        hOpciones.setAlignment(Pos.CENTER_LEFT);
    }

    public void crearUIJuego(){
        crearGridCartas(5);
        crearUIJugadores();
    }
    public void crearYMostrarEscena(){
        escena = new Scene(pnlPrincipal, 1200, 790);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setScene(escena);
        this.setTitle("Memorama");
        this.show();
    }

    public void crearGridCartas(int pares){ //reemplazar con (int pares) unido al txtFldPares
        //reemplazar con función del txtFldPares

        if(pares < 3 || pares >16){
            lblMensajeError = new Label("El numero escrito \n" +
                    "está fuera de los \n" +
                    "limites \n\n" +
                    "Escriba un \n" +
                    "número de pares \n" +
                    "entre 3 y 16");
            //lblMensajeError.getStyleClass().clear();
            lblMensajeError.getStyleClass().addAll("lbl-warning");
            return;
        }

        Label lblCarta = new Label("Reemplazar"); //Reemplazar con imagenes
        lblCarta.setFont(new Font("Arial", 10));
        int anchoCarta=100, altoCarta=200, filas = 0, columnas = 0;
        switch (pares) {
            case 3, 4, 5 -> {
                filas = 2;
                columnas = pares;
            }
            case 6, 7, 8, 9 -> {
                filas = 3;
                if(pares == 6) {
                    columnas = 5;
                }
                else{ columnas = 6; }
            }
            case 10, 11, 12 ->{
                filas = 4;
                columnas = 6;
            }
            case 13, 14, 15, 16 ->{
                filas = 4;
                columnas = 8;
            }
        }

        if(pares <= 5){anchoCarta=192; altoCarta=300;}
        else if(pares <=9){ anchoCarta = 158; altoCarta= 224;}
        else if(pares <= 12){anchoCarta=150; altoCarta=170;}
        else {anchoCarta=117; altoCarta=170;}
        //Limite donde no deja poner más pares

        btnCarta = new Button[filas][columnas];
        gridCartas = new GridPane();
        gridCartas.setPadding(new Insets(2));
        gridCartas.setHgap(6); gridCartas.setVgap(5);

        int contadorCartas = 1;
        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                if(contadorCartas <= pares*2){
                    btnCarta[i][j] = new Button();
                    btnCarta[i][j].setPrefSize(anchoCarta,altoCarta);
                    //btnCarta[i][j].setPadding(new Insets(10));
                    btnCarta[i][j].setGraphic(new Label("Reemplazar")); //Aquí pondré el lbl de la imageview
                    //Aqui irán las funciones de la carta
                    gridCartas.add(btnCarta[i][j], j, i);
                    contadorCartas++;
                }
            }
        }

        lblGridCartas.setGraphic(gridCartas);
    }
    public void crearUIJugadores(){
        Insets padding = new Insets(3);

        lblJ1 = new Label();
        Text txtJ1 = new Text("Jugador 1:");
        txtJ1.setFont(new Font("Arial", 20));
        lblJ1.setGraphic(txtJ1); lblJ1.setPadding(padding);
        lblJ1.getStyleClass().add("lbl-success");

        lblParesJ1 = new Label(); //añadir funcion para contar pares del jugador 1
        Text txtParesJ1 = new Text("0 puntos");
        txtParesJ1.setFont(new Font("Arial", 20));
        lblParesJ1.setGraphic(txtParesJ1); lblParesJ1.setPadding(padding);
        lblParesJ1.getStyleClass().add("lbl-success");

        hJ1 = new HBox(lblJ1, lblParesJ1);
        hJ1.setSpacing(5);

        lblJ2 = new Label();
        Text txtJ2 = new Text("Jugador 2:");
        txtJ2.setFont(new Font("Arial", 20));
        lblJ2.setGraphic(txtJ2); lblJ2.setPadding(padding);
        lblJ2.getStyleClass().add("lbl-danger");

        lblParesJ2 = new Label(); //añadir funcion para contar pares del jugador 1
        Text txtParesJ2 = new Text("0 puntos");
        txtParesJ2.setFont(new Font("Arial", 20));
        lblParesJ2.setGraphic(txtParesJ2); lblParesJ2.setPadding(padding);
        lblParesJ2.getStyleClass().add("lbl-danger");

        hJ2 = new HBox(lblJ2, lblParesJ2);
        hJ2.setSpacing(5);

        vJugadores = new VBox(hJ1, hJ2, lblMensajeError); vJugadores.setSpacing(20);
    }

    public void crearCosas(){
        int pares = Integer.parseInt(txtFldPares.getText());
        if(pares < 3 || pares >16){
            lblMensajeError.setText("El numero escrito \n" +
                    "está fuera de los \n" +
                    "limites \n\n" +
                    "Escriba un \n" +
                    "número de pares \n" +
                    "entre 3 y 16");
            lblMensajeError.getStyleClass().clear();
            lblMensajeError.getStyleClass().add("lbl-warning");
        }
        else {
            crearGridCartas(pares);
            lblMensajeError.setText("El número escrito está \n" +
                    "dentro de los limites \n" +
                    "correctos. \n\n" +
                    "Escriba otro numero \n" +
                    "entre 3 y 16 para \n" +
                    "volver a revolver.");
            lblMensajeError.getStyleClass().clear();
            lblMensajeError.getStyleClass().add("lbl-primary");
        }
    }
}
