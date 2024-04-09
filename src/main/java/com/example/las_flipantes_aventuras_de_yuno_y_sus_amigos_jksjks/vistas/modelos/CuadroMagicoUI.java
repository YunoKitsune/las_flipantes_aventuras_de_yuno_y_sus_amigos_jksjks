package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CuadroMagicoUI {
    public GridPane grdCuadrados;
    private VBox vPrincipal, vHudArriba, vMenuDerecha, vHud;
    private HBox hHud, hCuadros, hSumAbajo, hContenido, hHudAbajo;
    private Label lblHudMensajeArriba, lblMensajeError, lblGrdCuadrados, lblHudNumCuadradosLado, lblHudNumMinimo;
    private Label lblMenuSumaFijo, lblMenuSuma, lblMenuNumMinimoFijo, lblMenuNumMinimo, lblMenuNumMaximoFijo, lblMenuNumMaximo;
    private TextField txtNumCuadrosLado, txtNumPequeno;
    private Button btnCrearYResolver, btnReiniciar;
    private Button[][] btnContenedor;
    private final Font fuentePrincipal = new Font("Arial", 21);
    private final Font fuenteMenu = new Font("Arial", 15);
    public final Font fuenteMuyPequena = new Font("Arial", 10);
    public final Font fuentePequena = new Font("Arial", 15);
    public final Font fuenteMediana = new Font("Arial", 18);
    public final Font fuenteGrande = new Font("Arial", 30);

    public CuadroMagicoUI(){
        crearInterfaz();
    }
    public void crearInterfaz(){
        crearObjetosVisualesPrincpiales();
        crearYAnadirHud();
        crearYAnadirMenuDerecha();
        crearYAnadirCuadricula();


        hContenido.getChildren().addAll(lblGrdCuadrados, vMenuDerecha);
        hContenido.setPadding(new Insets(5));
        hContenido.setSpacing(20);
        vPrincipal.getChildren().addAll(vHud, hContenido);
    }
    public void crearObjetosVisualesPrincpiales(){
        vPrincipal = new VBox();
        vPrincipal.setPadding(new Insets(5));
        vPrincipal.setPrefSize(1520, 780);
        vHud = new VBox();
        hHudAbajo = new HBox();

        hCuadros = new HBox();
        hCuadros.setAlignment(Pos.CENTER);
        vMenuDerecha = new VBox();
        vMenuDerecha.setAlignment(Pos.CENTER_RIGHT);
        hContenido = new HBox();
        hContenido.setAlignment(Pos.CENTER_RIGHT);

        grdCuadrados = new GridPane();
    }
    public void crearYAnadirHud(){
        crearLabelsHud();
        crearComponentesHud();
        anadirCosasDeHud();
    }
    private void crearYAnadirMenuDerecha(){
        crearMenuDerecha();
        anadirCosasDeMenuDerecha();
    }
    private void crearYAnadirCuadricula(){
        anadirCuadricula();
    }

    private void anadirCuadricula(){
        lblGrdCuadrados = new Label();
        lblGrdCuadrados.setGraphic(grdCuadrados);
        lblGrdCuadrados.setAlignment(Pos.BOTTOM_LEFT);
        hContenido.setAlignment(Pos.TOP_CENTER);
    }

    private void crearLabelsHud(){
        lblHudMensajeArriba = new Label(
                "Escribe los números especificados en los limites y presione el boton \"Crear\"");
        lblHudMensajeArriba.setPrefHeight(50);
        lblHudMensajeArriba.setFont(fuentePrincipal);

        lblHudNumCuadradosLado = new Label("Numero de cuadrados por lado (impar entre 3 y 23):");
        lblHudNumCuadradosLado.setFont(fuentePrincipal);

        lblHudNumMinimo = new Label("El número más pequeño que aparecerá (de 0 a 9,999,000):");
        lblHudNumMinimo.setFont(fuentePrincipal);
    }
    private void crearComponentesHud(){
        txtNumCuadrosLado = new TextField();
        txtNumCuadrosLado.setMaxWidth(70);
        txtNumCuadrosLado.setFont(fuentePrincipal);

        txtNumPequeno = new TextField();
        txtNumPequeno.setMaxWidth(130);
        txtNumPequeno.setFont(fuentePrincipal);

        btnCrearYResolver = new Button("Crear");
        btnCrearYResolver.setFont(fuentePrincipal);
        btnCrearYResolver.setPrefSize(180, 30);

    }

    private void anadirCosasDeHud(){
        vHud.getChildren().add(lblHudMensajeArriba);

        hHudAbajo.getChildren().addAll(lblHudNumCuadradosLado, txtNumCuadrosLado, lblHudNumMinimo, txtNumPequeno, btnCrearYResolver);
        hHudAbajo.setSpacing(20);
        hHudAbajo.setAlignment(Pos.CENTER_LEFT);

        vHud.getChildren().add(hHudAbajo);

    }
    private void anadirCosasDeMenuDerecha(){
        vMenuDerecha = new VBox(lblMenuSumaFijo, lblMenuSuma, lblMenuNumMinimoFijo, lblMenuNumMinimo, lblMenuNumMaximoFijo, lblMenuNumMaximo);
        vMenuDerecha.setAlignment(Pos.TOP_LEFT);
        vMenuDerecha.setSpacing(10);
    }
    private void crearMenuDerecha(){
        lblMenuSumaFijo = new Label("La suma hacia\ntodos los lados\nda como resultado:");
        lblMenuSumaFijo.setFont(fuenteMenu);
        lblMenuSuma = new Label("2,000,000,000");
        lblMenuSuma.setFont(fuenteMenu);

        lblMenuNumMinimoFijo = new Label("El numero minimo es:");
        lblMenuNumMinimoFijo.setFont(fuenteMenu);
        lblMenuNumMinimo = new Label("9,999,000");
        lblMenuNumMinimo.setFont(fuenteMenu);

        lblMenuNumMaximoFijo = new Label("El numero máximo es:");
        lblMenuNumMaximoFijo.setFont(fuenteMenu);
        lblMenuNumMaximo = new Label("9,999,999");
        lblMenuNumMaximo.setFont(fuenteMenu);
    }



    //SETTERS y GETTERS
    public VBox getvPrincipal() {
        return vPrincipal;
    }
    public void setvPrincipal(VBox vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
    public Label getLblMenuSuma() {
        return lblMenuSuma;
    }
    public void setLblMenuSuma(Label lblMenuSuma) {
        this.lblMenuSuma = lblMenuSuma;
    }
    public Label getLblMenuNumMinimo() {
        return lblMenuNumMinimo;
    }
    public void setLblMenuNumMinimo(Label lblMenuNumMinimo) {
        this.lblMenuNumMinimo = lblMenuNumMinimo;
    }
    public Label getLblMenuNumMaximo() {
        return lblMenuNumMaximo;
    }
    public void setLblMenuNumMaximo(Label lblMenuNumMaximo) {
        this.lblMenuNumMaximo = lblMenuNumMaximo;
    }
    public GridPane getGrdCuadrados() {
        return grdCuadrados;
    }
    public void setGrdCuadrados(GridPane grdCuadrados) {
        this.grdCuadrados = grdCuadrados;
    }
    public TextField getTxtNumCuadrosLado() {
        return txtNumCuadrosLado;
    }
    public void setTxtNumCuadrosLado(TextField txtNumCuadrosLado) {
        this.txtNumCuadrosLado = txtNumCuadrosLado;
    }
    public TextField getTxtNumPequeno() {
        return txtNumPequeno;
    }
    public void setTxtNumPequeno(TextField txtNumPequeno) {
        this.txtNumPequeno = txtNumPequeno;
    }
    public Button getBtnCrearYResolver() {
        return btnCrearYResolver;
    }
    public void setBtnCrearYResolver(Button btnCrearYResolver) {
        this.btnCrearYResolver = btnCrearYResolver;
    }
    public Label getLblHudMensajeArriba() {
        return lblHudMensajeArriba;
    }
    public void setLblHudMensajeArriba(Label lblHudMensajeArriba) {
        this.lblHudMensajeArriba = lblHudMensajeArriba;
    }
    public Label getLblGrdCuadrados() {
        return lblGrdCuadrados;
    }
    public void setLblGrdCuadrados(Label lblGrdCuadrados) {
        this.lblGrdCuadrados = lblGrdCuadrados;
    }
}
