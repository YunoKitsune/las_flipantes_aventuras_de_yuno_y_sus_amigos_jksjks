package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.Impresion;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.Documento_impresion;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class HiloImpresion extends Thread{ //Pero que clase màs problematica AAaAaAaaaAAAaaAAaAaaAAAA
    private Documento_impresion documento;
    private ProgressBar progressBarImpresion;
    private Panel pnlImpresiones;
    private GridPane grdImpresiones;
    public Impresion impresion;
    private volatile boolean running; //Bandera para controlar el estado del hilo, por alguna razon no funciona si no le pongo volatile
    private volatile boolean pausado; //Bandera para pausar el hilo lo mismo con la anterior XD


    @Override
    public void run() { //Lo que hace cuando le pones start(), que mètodo màs problemàtico >n<
        super.run();
        //Le dice a la impresion què hilo hay actualmente
        impresion.setHiloImprimiendoActualmente(this);
        //Pone todas las variables iniciales
        running = true; pausado = false;
        Platform.runLater(() -> impresion.cambiarBtnADetener() ); //Por si no alcanzò a detener en la ùltima pàgina
        double avancePorPagina = (double) 1 / documento.getPaginas(), avanceActual=0;
        int paginaActual=0;
        long tiempoPagina = 800;

        //Hace que avance la barra, termina cuando termine de imprimir todas las pàginas
        while( running && paginaActual < documento.getPaginas()){
            //si la variable "pausado" es true, se espera hasta que le demos "notify" y el "paused" sea false
            synchronized (this){
                while(pausado){
                    try{
                        wait();
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }
            //Se espera a que pase el tiempo suficiente para que imprima una hoja
            try{
                Thread.sleep(tiempoPagina);
            }
            catch(Exception e){ //Si se buguea, matamos el proceso porque no pudo imprimir la hoja (Lo quise hacer lo mas parecido a una impresora XD)
                Thread.currentThread().interrupt();
                break;
            }
            //Ya luego que terminò de "imprimir la hoja", avanzarà la barrita
            paginaActual++;
            avanceActual += avancePorPagina ;
            progressBarImpresion.setProgress(avanceActual);
        } //Fin del while o "la impresion"

        //Esto lo puse como prueba para ver si podia detenerse con un simple "return", lo dejè porque no tuve tiempo de probar si funciona como creo
        if (!running) { return; }

        //Les baja el numero a todos los documentos cuando termine de imprimirse
        Documento_impresion documentoBandera = documento;
        while(documentoBandera != null){
            documentoBandera.setNoArchivo(documentoBandera.getNoArchivo()-1);
            documentoBandera = documentoBandera.getDocumentoSiguiente();
        }

        //Luego actualiza el grid (Sin el Platform.runLater da error de Not a FX Application o algo asi)
        Platform.runLater(() -> impresion.reorganizarGrid(documento) );


        //Al final le dice al siguiente documento que se imprima (decirle al siguiente hilo que se ejecute)
        if(documento.getDocumentoSiguiente() != null){
            documento.getDocumentoSiguiente().getHiloImpresion().start();
        }
    }

    //Metodo que usa Impresion para activar el wait en el hilo actual dentro de run()
    public void pausar(){
        pausado = true;
    }

    //Desactiva el wait() y pone pausado como false para que salga del while y siga imprimiendo
    public void seguirImprimiendo(){
        synchronized (this){
            pausado = false; //pausado=true para que salga del while (lo puse dentro de while para asegurarme por si acaso para que no ejecute lo que iba despuès)
            notify(); //desactiba el wait() para que siga ejecutando run()
        }
    }

    //Mata el proceso, le puse el running false por si algo sale mal y el interrupt no servìa, para mmi suerte, sirve si el hilo no està pausado.
    public void detener() {
        running = false;
        this.interrupt();
    }

    //Setters y Getters
    public ProgressBar getProgressBarImpresion() {
        return progressBarImpresion;
    }

    public void setProgressBarImpresion(ProgressBar progressBarImpresion) {
        this.progressBarImpresion = progressBarImpresion;
    }
    public Documento_impresion getDocumento() {
        return documento;
    }

    public void setDocumento(Documento_impresion documento) {
        this.documento = documento;
    }
    public Panel getPnlImpresiones() {
        return pnlImpresiones;
    }
    public void setPnlImpresiones(Panel pnlImpresiones) {
        this.pnlImpresiones = pnlImpresiones;
    }

    public GridPane getGrdImpresiones() {
        return grdImpresiones;
    }

    public void setGrdImpresiones(GridPane grdImpresiones) {
        this.grdImpresiones = grdImpresiones;
    }
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
}
