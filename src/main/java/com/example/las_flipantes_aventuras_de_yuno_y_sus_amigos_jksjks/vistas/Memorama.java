package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Memorama extends Stage {
    public Scene escena;
    public HBox hPrincipal, hOpciones, hLblPares, hTxtfldPares, hBtnRevolver, hTimer;
    public HBox hJuego, hJ1, hJ2, hParesJ1, hParesJ2;
    public VBox vPrincipal, vJugadores;
    public GridPane gridCartas;
    public Label lblNoPares, lblTxtFldPares, lblBtnRevolver, lblTimer, lblGridCartas, lblJ1, lblJ2, lblParesJ1, lblParesJ2;
    public TextField txtFldPares;
    public Button btnRevolver;
    public Button[][] btnCarta;

    public Memorama(){
        crearUI();
        cargarUIsAlUIPirncipal();
        crearYMostrarEscena();
    }
    public void crearUI(){
        crearUIPrincipal();
        crearUIOpciones();
        crearUIJuego();
    }
    public void crearUIPrincipal(){ //Crea el espacio de la interfaz
        hPrincipal = new HBox();//Dentro del hPrincipal irá el vPrincipal
        vPrincipal = new VBox();
        hOpciones = new HBox(); //Dentro de vPrincipal irá hOpciones y hJuego
        hJuego = new HBox();
    }
    public void crearUIOpciones(){
        lblNoPares = new Label("No. pares:");
        lblNoPares.setPrefSize(125, 30);
        lblNoPares.setFont(new Font("Arial", 26));
        lblNoPares.setAlignment(Pos.BOTTOM_CENTER);
        //hLblPares = new HBox(lblNoPares);

        txtFldPares = new TextField("0");
        txtFldPares.setPrefSize(200, 30);
        txtFldPares.setFont(new Font("Arial", 26));

        //lblTxtFldPares = new Label("",txtFldPares);
        //hTxtfldPares = new HBox(txtFldPares);

        btnRevolver = new Button("Revolver");
        btnRevolver.setPrefSize(200, 30);
        btnRevolver.setFont(new Font("Arial", 26));
        //hBtnRevolver = new HBox(btnRevolver);

        lblTimer = new Label("00:00");
        lblTimer.setPrefSize(200, 30);
        lblTimer.setFont(new Font("Arial", 26));
        //hTimer = new HBox(lblTimer);

        hOpciones.getChildren().addAll(lblNoPares, txtFldPares, btnRevolver, lblTimer);
        hOpciones.setPrefSize(1200, 70);
        hOpciones.setSpacing(20);
        hOpciones.setAlignment(Pos.CENTER_LEFT);
    }
    public void crearUIJuego(){
        crearGridCartas();
        crearUIJugadores();
        hJuego.getChildren().addAll(gridCartas,vJugadores);
    }
    public void cargarUIsAlUIPirncipal(){
        vPrincipal.getChildren().addAll(hOpciones, hJuego);
        hPrincipal.getChildren().addAll(vPrincipal);
    }
    public void crearYMostrarEscena(){
        escena = new Scene(hPrincipal, 1225, 720);
        this.setScene(escena);
        this.setTitle("Memorama");
        this.show();
    }

    public void crearGridCartas(){ //reemplazar con (int pares) unido al txtFldPares
        int pares = 5; //reemplazar con función del txtFldPares
        Label lblCarta = new Label("Reemplazar"); //Reemplazar con imagenes
        lblCarta.setFont(new Font("Arial", 10));

        int anchoCarta=100, altoCarta=200, filas=2, columnas=pares;
        if(pares <= 5){anchoCarta=200; altoCarta=300;}
        else if(pares <= 10){anchoCarta=150; altoCarta=200;}
        else if(pares <= 20){anchoCarta=100; altoCarta=150;}
        else if(pares <= 40){anchoCarta=100; altoCarta=150; filas=4; columnas = pares/2;}
        else{} //Limite donde no deja poner más pares

        btnCarta = new Button[filas][columnas];
        gridCartas = new GridPane();
        gridCartas.setPadding(new Insets(5));

        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                btnCarta[i][j] = new Button();
                btnCarta[i][j].setPrefSize(anchoCarta,altoCarta);
                //btnCarta[i][j].setPadding(new Insets(10));
                btnCarta[i][j].setGraphic(new Label("Reemplazar")); //Aquí pondré el lbl de la imageview
                //Aqui irán las funciones de la carta
                gridCartas.add(btnCarta[i][j], j, i);
            }
        }
    }
    public void crearUIJugadores(){
        lblJ1 = new Label("Jugador 1:"); lblJ1.setFont(new Font("Arial", 20));
        lblParesJ1 = new Label("0 puntos"); //añadir funcion para contar pares del jugador 1
        lblParesJ1.setFont(new Font("Arial", 20));
        hJ1 = new HBox(lblJ1, lblParesJ1);
        hJ1.setSpacing(5);

        lblJ2 = new Label("Jugador 2:"); lblJ2.setFont(new Font("Arial", 20));
        lblParesJ2 = new Label("0 puntos"); //añadir funcion para contar pares del jugador 1
        lblParesJ2.setFont(new Font("Arial", 20));
        hJ2 = new HBox(lblJ2, lblParesJ2);
        hJ2.setSpacing(5);

        hJuego.setSpacing(20);
        vJugadores = new VBox(hJ1, hJ2); vJugadores.setSpacing(20);
    }

   /* public void revolverCartas(){
        String[] arImagenes = {"img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg"};

        Image[][] arCartas = new Image[2][5];
        Image carta;
        ImageView imvCarta;

        int posx =0, posy=0, cont=0;
        for(int i=0; i<arImagenes.length;){
            posx = (int) (Math.random() *2);
            posy = (int) (Math.random() *2);
            if( arBtnCartas[posx][posy] == null){
                arBtnCartas[posx][posy] = new Button();
                imvCarta = new ImageView(getClass().getResource("/img/"+arImagenes[i]).toString());
                imvCarta.setFitWidth(100); imvCarta.setFitHeight(150);
                arBtnCartas[posx][posy].setGraphic(imvCarta);
                arBtnCartas[posx][posy].setPrefSize(100, 150);
                gdpJuego.add(arBtnCartas[posx][posy], posy, posx)
                cont++;
                if( cont==2 ){cont =0; i++;}
            }
        }
    }*/
}
