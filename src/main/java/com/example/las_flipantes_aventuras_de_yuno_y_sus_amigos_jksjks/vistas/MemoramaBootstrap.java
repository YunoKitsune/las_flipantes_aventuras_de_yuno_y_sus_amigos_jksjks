package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class MemoramaBootstrap extends Stage {
    public Scene escena;
    public Panel pnlPrincipal;
    public GridPane gridPane;
    public Label lblNoPares, lblTimer;
    public TextField txtFldPares;
    public Button btnRevolver;
    public Button[][] btnGrd;
    public ImageView imv;

    public MemoramaBootstrap(){
        crearUI();
        escena = new Scene(pnlPrincipal, 1225, 720);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setScene(escena);
        this.setTitle("Memorama");
        this.show();
    }

    public void crearUI(){
        crearUIOpciones();
        ponerUIEnPanel();
    }
    public void crearUIOpciones() {
        lblNoPares = new Label();
        Text text = new Text("No. pares:");
        text.setFont((new Font("Arial", 26)));
        //text.getStyleClass().addAll("h1");
        //lblNoPares.setText(text.getText());
        lblNoPares.setGraphic(text);
        lblNoPares.setPadding(new Insets(5));
        //lblNoPares.setPrefSize(125, 30);
        //lblNoPares.setFont(new Font("Arial", 26));
        //lblNoPares.setAlignment(Pos.BOTTOM_CENTER);


        txtFldPares = new TextField("0");
        txtFldPares.setPrefSize(200, 30);
        txtFldPares.setFont(new Font("Arial", 26));


        btnRevolver = new Button("Revolver");
        btnRevolver.setPrefSize(200, 30);
        btnRevolver.setFont(new Font("Arial", 26));
        lblTimer = new Label("00:00");
        lblTimer.setPrefSize(200, 30);
        lblTimer.setFont(new Font("Arial", 26));

    }

    public void ponerUIEnPanel(){
        pnlPrincipal = new Panel();
        pnlPrincipal.getStyleClass().add("panel-primary");
        lblNoPares.getStyleClass().addAll("lbl-success");
        //lblNoPares.getStyleClass().clear();
        //lblNoPares.getStyleClass().add("lbl-danger");
        pnlPrincipal.setRight(lblNoPares);
        pnlPrincipal.setPadding(new Insets(5));
    }
}


