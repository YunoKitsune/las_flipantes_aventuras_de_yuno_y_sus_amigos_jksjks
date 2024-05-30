package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes.HiloImpresion;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.Documento_impresion;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class Impresion extends Stage {
    private Scene escena;
    private HBox hAbajo, hArriba;
    private VBox vPrincipal, vAbajo, vArriba;
    private Panel pnlPrincipal;
    private GridPane grdImpresiones;
    private Timer tiempo;
    private TimerTask tarea;
    private Button btnTarea, btnPausarSeguir;
    private Font fuenteGrande = new Font("Arial", 16);
    private Font fuenteMediana = new Font("Arial", 12);
    private Label lbl;
    private String[] extensiones = {"pdf", "docx", "xlsx", "ppx", "txt"}; //Extensiones para usarlas en el nombre de los archivos de manera aleatoria.
    private Documento_impresion ultimoDocumento, documentoImprimiendo;
    private HiloImpresion hiloImprimiendoActualmente;

    //Constructor para crear toda la interfaz inicial
    public Impresion(){
        crearComponentesArriba();
        crearComponentesAbajo();
        crearComponentesCentro();

        pnlPrincipal = new Panel();

        pnlPrincipal.setHeading(vArriba);
        pnlPrincipal.setCenter(grdImpresiones);
        pnlPrincipal.setFooter(hAbajo);
        crearYMostrarEscena();
    }
    //Crea la escena para la interfaz y muestra todo.
    private void crearYMostrarEscena(){
        escena = new Scene(pnlPrincipal, 1200, 790);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setScene(escena);
        this.setTitle("Impresion");
        this.show();
        //Cuando presione la X pregunta si realmente quieres salir, si lo hace, mata los hilos.
        this.setOnCloseRequest(e -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Salir uwu");
            alerta.setHeaderText("Deveras?");
            alerta.setContentText("Quieres salir? deveras? ^w^/");
            ButtonType deveras = new ButtonType("Deveras!!");
            ButtonType nelKrnal = new ButtonType("Nel krnal >w<");
            alerta.getButtonTypes().setAll(deveras, nelKrnal);
            Optional<ButtonType> opc = alerta.showAndWait();
            if(opc.get() == deveras){
                if(hiloImprimiendoActualmente != null){
                    if(hiloImprimiendoActualmente.isPausado()){
                        hiloImprimiendoActualmente.seguirImprimiendo();
                    }//Para que no crashee, le puse que si està pausado haga como que va a imprimir y luego mata el hilo.
                    hiloImprimiendoActualmente.detener();
                }
            }
            else { e.consume(); // Cancela el cierre si el usuario elige "Nel krnal"
            }
        });
    }
    //Crea los botones de abajo
    private void crearComponentesAbajo(){
        btnTarea = new Button("Agregar tarea");
        //btnTarea.setFont(fuenteGrande);
        btnTarea.setStyle("-fx-font-size: 22px;");
        btnTarea.setPrefSize(200, 40);
        btnTarea.setAlignment(Pos.CENTER);
        btnTarea.setOnAction((event) -> crearImpresion()); //Al presionar esto, crea una impresion

        btnPausarSeguir = new Button("Detener");
        btnPausarSeguir.setStyle("-fx-font-size: 22px;");
        btnPausarSeguir.setPrefSize(200, 40);
        btnPausarSeguir.setAlignment(Pos.CENTER);
        btnPausarSeguir.setOnAction((event) -> {
            //Aqui va pausar el hilo actual y cambia el boton para mostrar "Seguir"
                hiloImprimiendoActualmente.pausar();
                cambiarBtnASeguir();
        });
        //Ya nomas los añade al HBox de abajo
        hAbajo = new HBox(btnTarea, btnPausarSeguir);
    }
    //Crea el mensaje de arriba.
    private void crearComponentesArriba(){
        lbl = new Label("Presione el boton ''Agregar tarea'' para agregar una impresion " +
                "a la cola de impresion, al terminar una impresion, se seguirá a la siguiente.\n\n" +
                "Al presionar ''Detener'' se detendrá la impresion, seguirá hasta presionar ''continuar''.");
        lbl.setFont(fuenteGrande);
        lbl.setStyle("-fx-font-size: 18px;");
        lbl.setPadding(new Insets(5));
        lbl.getStyleClass().add("lbl-info");
        vArriba = new VBox(lbl);
    }
    //Crea el grid de en medio
    private void crearComponentesCentro(){
        grdImpresiones = new GridPane();
        grdImpresiones.setVgap(5);
        grdImpresiones.setHgap(5);
        grdImpresiones.setAlignment(Pos.TOP_LEFT);
        grdImpresiones.setPadding(new Insets(20));
    }

    //Mètodo para crear la impresion.
    private void crearImpresion(){
        //Este if será el que valide si puede entrar un archivo nuevo o no (El limite es de 23 archivos en cola)
        if(ultimoDocumento!= null && ultimoDocumento.getNoArchivo() == 23){
            //Si ya es el limite, muestra un mensaje advirtiendo de eso y no hace nada màs
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Así que 23 es el límite O.O");
            alerta.setHeaderText("Alcancaste el limite de archivos!!!");
            alerta.setContentText("Tienes que esperar a que termine de imprimirse\n" +
                    "al menos un archivo para seguir imprimiendo.\n Srry >n<");
            ButtonType nimodoUnU = new ButtonType("Ps nimodo UnU");
            alerta.getButtonTypes().setAll(nimodoUnU);
            alerta.show();
        } else{
            //Si son menos de 23 harà un nuevo documento con una extension aleatoria y el numero de archivo del que siga del ultimo archivo puesto
            Documento_impresion nuevoDocumento = new Documento_impresion(extensiones[(int)(Math.random() * 4)] ); //La extension se pone aleatoriamente
            if(ultimoDocumento != null && ultimoDocumento.getNoArchivo() != 0){
                nuevoDocumento.setNoArchivo(ultimoDocumento.getNoArchivo() + 1);
            }
            else{
                nuevoDocumento.setNoArchivo(1);
            }

            //Crea el label que aparece en el grid
            Label lblNombreDocumento = new Label(nuevoDocumento.getNombre()+ ", "+ nuevoDocumento.getPaginas()+" páginas.");
            lblNombreDocumento.setStyle("-fx-font-size: 16px;");

            //Creamos el PrgressBar
            ProgressBar progressBarDocumento = new ProgressBar(0);
            progressBarDocumento.setPrefSize(300, 35);

            //Creo el hilo del documento y le asigno los apuntadores del documento y del progressBar para usarlos despuès.
            HiloImpresion hiloDocumentoActual = new HiloImpresion();
            hiloDocumentoActual.setDocumento(nuevoDocumento);
            hiloDocumentoActual.setProgressBarImpresion(progressBarDocumento);

            //Por si acaso, tambien le digo al documento que hilo tiene asignado, ademàs del progressBar y el label por si acaso para desupuès
            nuevoDocumento.setHiloImpresion(hiloDocumentoActual);
            nuevoDocumento.setPgbImpresion(progressBarDocumento);
            nuevoDocumento.setLblNombre(lblNombreDocumento);

            //Le añado al grid el progressBar y el nombre del documento debajo del ultimo documento hecho
            grdImpresiones.add(nuevoDocumento.getPgbImpresion(), 0, nuevoDocumento.getNoArchivo());
            grdImpresiones.add(lblNombreDocumento, 1, nuevoDocumento.getNoArchivo());
            pnlPrincipal.setCenter(grdImpresiones);

            //Para que el grid se modifique dinàmicamente, el hilo debe saber sobre el grid, el panel y la impresion en general (si, me pude ahorrar pasos, pero ya nimodo uwu)
            hiloDocumentoActual.setGrdImpresiones(grdImpresiones);
            hiloDocumentoActual.setPnlImpresiones(pnlPrincipal);
            hiloDocumentoActual.impresion = this;

            //Si el numero asignado previamente es el 1 quiere decir que no hay archivos en cola, por lo que empieza a imprimirse
            if(nuevoDocumento.getNoArchivo() == 1){
                ultimoDocumento = nuevoDocumento;
                ultimoDocumento.getHiloImpresion().start(); }
            else{ //Si hay documentos en cola, los asigna como el siguiente documento a imprimir y lo pone como el ultimo documento que hay.
                ultimoDocumento.setDocumentoSiguiente(nuevoDocumento);
                ultimoDocumento = nuevoDocumento;
            }
        }
    }

    //Este metodo lo usa el hilo para quitar una impresion que se haya completado
    public void reorganizarGrid(Documento_impresion documentoBandera){
        Platform.runLater(() -> {
            //Tuve que poner este nuevo documento de variable porque no me dejaba usar el que metì como paràmetro XD
                    Documento_impresion finalDocumentoBandera = documentoBandera;
                    crearComponentesCentro();
                    int i = 0;
                    while (finalDocumentoBandera != null) { //Recorre todos los documentos de abajo para volver a acomodarlos
                        if (finalDocumentoBandera.getNoArchivo() > 0) {
                            grdImpresiones.add(finalDocumentoBandera.getPgbImpresion(), 0, i);
                            grdImpresiones.add(finalDocumentoBandera.getLblNombre(), 1, i);
                            i++;
                        }
                        finalDocumentoBandera = finalDocumentoBandera.getDocumentoSiguiente();
                    }
                    pnlPrincipal.setCenter(grdImpresiones);
                }
        );
    }

    public void cambiarBtnASeguir(){
        btnPausarSeguir.setText("Seguir");
        btnPausarSeguir.setOnAction((event) -> {
            //Aqui va detener el hilo actual
            if(hiloImprimiendoActualmente != null && ultimoDocumento.getNoArchivo() > 0){
                synchronized (hiloImprimiendoActualmente){
                    hiloImprimiendoActualmente.seguirImprimiendo();
                    hiloImprimiendoActualmente.notify();
                }
            }
            cambiarBtnADetener();
        });
    }

    public void cambiarBtnADetener(){
        btnPausarSeguir.setText("Detener");
        btnPausarSeguir.setOnAction((event) -> {
            //Aqui va detener el hilo actual
            if(hiloImprimiendoActualmente != null && ultimoDocumento.getNoArchivo() > 0){
                //try {
                    synchronized (hiloImprimiendoActualmente){
                        hiloImprimiendoActualmente.pausar();
                    }
                /*} catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                cambiarBtnASeguir();
            }
        });
    }

    public HiloImpresion getHiloImprimiendoActualmente() {
        return hiloImprimiendoActualmente;
    }

    public void setHiloImprimiendoActualmente(HiloImpresion hiloImprimiendoActualmente) {
        this.hiloImprimiendoActualmente = hiloImprimiendoActualmente;
    }
    public void setDocumentoImprimiendo(Documento_impresion documento){
        this.documentoImprimiendo = documento;
        reorganizarGrid(documentoImprimiendo);
    }
}