package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.*;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.Conexion;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.MemoramaBootstrap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private MenuBar menuBarPrincipal;
    private Menu menuParcial1, menuParcial2, menuSalida;
    private MenuItem mitCalculadora, mitMemorama, mitSalir, mitEmpleado, mitCuadroMagico, mitPista;
    private BorderPane bdpPanel;

    @Override
    public void start(Stage stage) throws IOException {
        crearMenu();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        bdpPanel = new BorderPane();
        bdpPanel.setTop(menuBarPrincipal);
        Scene scene = new Scene(bdpPanel);
        scene.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        Conexion.crearConexion();

        //new Calculadora();
    }

    private void crearMenu(){

        //Menu parcial 1:
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitMemorama = new MenuItem("Memorama");
        mitMemorama.setOnAction(event -> new Memorama());
        mitEmpleado = new MenuItem("Empleado Taqueria");
        mitEmpleado.setOnAction(event -> new EmpleadoTaqueria());
        mitCuadroMagico = new MenuItem("Cuadro mágico");
        mitCuadroMagico.setOnAction(event -> new CuadroMagico());

        mitPista = new MenuItem("Pista");
        mitPista.setOnAction(event -> new Pista());


        menuParcial1 = new Menu("Primer parcial");
        menuParcial1.getItems().addAll(mitCalculadora, mitMemorama, mitEmpleado, mitCuadroMagico);

        //Menu parcial 2:
        menuParcial2 = new Menu("Segundo parcial");
        menuParcial2.getItems().addAll(mitPista);

        //Menu salir:
        menuSalida = new Menu("Salir");
        mitSalir = new MenuItem("Salir");
        mitSalir.setOnAction(event -> System.exit(0));
        menuSalida.getItems().addAll(mitSalir);

        //Agrega todos los menus al menuBar
        menuBarPrincipal = new MenuBar();
        menuBarPrincipal.getMenus().addAll(menuParcial1, menuParcial2, menuSalida);

        // https://th.bing.com/th/id/R.13bd6ced37ddd70847e25d12be240ed6?rik=KhVbwpM6CfTXRQ&riu=http%3a%2f%2fsavepoint.es%2fwp-content%2fuploads%2f2015%2f05%2fLOVE-LIVE-SIF.png&ehk=W%2bwFwJDBsjUYGNuRminfCF6D0CxSGTKk%2fSce%2bU8Xnkc%3d&risl=&pid=ImgRaw&r=0
    }

    public static void main(String[] args) {
        launch();
    }
}