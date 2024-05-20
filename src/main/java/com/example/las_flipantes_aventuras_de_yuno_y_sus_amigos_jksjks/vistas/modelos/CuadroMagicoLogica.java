package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.CuadroMagico;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.*;

public class CuadroMagicoLogica {
    private CuadroMagicoUI interfaz;
    private long posicion;
    private int numeroMinimo, numeroActual, numeroLimite;
    private int cantidadNumerosPorLado = 3;
    private int cantidadBytesPorEntero = 4, cantidadBytesPorLinea, columnaActual, lineaActual, columnaARevisar, lineaARevisar;
    private String nombreArchivo;
    public RandomAccessFile accesoArchivo;
    public File archivo;
    public CuadroMagicoLogica(){
        nombreArchivo = "cuadroMagico.txt";
        try {
            eliminarArchivo(); //Elimina el archivo en caso de que haya uno anterior
            crearFlujoAArchivo();
            escribirTodasLasLineasCon0();
            rellenarArchivo();
            mostrarArchivoEnGrid();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
    public CuadroMagicoLogica(CuadroMagicoUI interfaz){
        this.interfaz = interfaz;
        nombreArchivo = "cuadroMagico.txt";
        try {
            eliminarArchivo(); //Elimina el archivo en caso de que haya uno anterior
            crearFlujoAArchivo();
            escribirTodasLasLineasCon0();
            rellenarArchivo();
            mostrarArchivoEnGrid();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private void crearFlujoAArchivo() throws IOException{
        accesoArchivo = new RandomAccessFile(nombreArchivo, "rw");
    }

    private void eliminarArchivo(){
        archivo = new File(nombreArchivo);
        if(archivo.exists()){
            archivo.delete();
        }
    }

    //Rellena toda la cuadricula del cuadro mágico
    public void rellenarArchivo() throws IOException{
        try {
            cantidadNumerosPorLado = Integer.parseInt(interfaz.getTxtNumCuadrosLado().getText());
            numeroMinimo = Integer.parseInt(interfaz.getTxtNumPequeno().getText());
        }
        catch (Exception e){System.out.println("Valores no válidos"); mensajeErrorFormato(); return;}
        if(cantidadNumerosPorLado < 3 || cantidadNumerosPorLado >25 || cantidadNumerosPorLado%2 == 0 || numeroMinimo>9999000){
            System.out.println("Numeros no validos, introduzca otros numero");
            mensajeErrorNumeros();
            return;
        }

        //numeroMinimo=1;
        //numeroMinimo = Integer.parseInt(interfaz.getTxtNumPequeno().getText());
        escribirTodasLasLineasCon0();
        escribirPrimerNumero();
        //numeroActual = numeroMinimo+1;
        numeroLimite = (numeroMinimo-1) + (cantidadNumerosPorLado*cantidadNumerosPorLado);
        while(numeroActual < numeroLimite){
            escribirSiguientesNumeros();
        }
        mensajeExito();
    }

    private void escribirSiguientesNumeros() throws IOException{
        int numeroARevisar=0;
        columnaARevisar = columnaActual +1;
        lineaARevisar = lineaActual -1;
        if(columnaARevisar >= cantidadNumerosPorLado){ //Si sobrepasa el limite a la derecha
            columnaARevisar = 0; //Volvemos a la primer columna
        }
        if(lineaARevisar < 0){ //Si sobrepasa el limite hacia arriba
            lineaARevisar = cantidadNumerosPorLado-1; //Vamos a la línea de hasta abajo
        }
        posicion = ((long) cantidadBytesPorLinea *lineaARevisar) + (lineaARevisar* 2L) +  ((long) cantidadBytesPorEntero * columnaARevisar);
        accesoArchivo.seek(posicion);
        numeroARevisar = accesoArchivo.readInt();

        //Después de todo lo anterior, vamos a ver si ese espacio ya está ocupado
            while(numeroARevisar != 0){ //Si al revisar la posición, ya hay un número escrito, bajamos una linea
               bajarUnaLinea();
               numeroARevisar = accesoArchivo.readInt();
            }
            //Ya ubicamos un cuadro que no está ocupado, ahora las diremos que la columna y fila revisada será la que usaremos actualmente
            columnaActual = columnaARevisar; lineaActual = lineaARevisar;
            posicion = ((long) cantidadBytesPorLinea *lineaActual) + (lineaActual* 2L) +  ((long) cantidadBytesPorEntero * columnaActual);
                   //Bytes de:      contenidos de cada linea ||         Saltos de linea ||   Numeros en la linea anteriores al que se escribirá
            accesoArchivo.seek(posicion);
            numeroActual++;
            accesoArchivo.writeInt(numeroActual);

    }
    private void bajarUnaLinea() throws IOException {
        lineaARevisar = lineaActual+1; //Bajamos una línea desde la línea anterior
        columnaARevisar = columnaActual; //Regresamos a la columna original
        if(lineaARevisar >= cantidadNumerosPorLado){ //En raro caso que al bajar y que no haya más cuadros abajo, volvemos a un cuadro de arriba
            lineaARevisar = 0;
        }
        //Volvemos a posicionarnos en la posicion actual
        posicion = ((long) cantidadBytesPorLinea *lineaARevisar) + (lineaARevisar* 2L) +  ((long) cantidadBytesPorEntero * columnaARevisar);
        accesoArchivo.seek(posicion);
    }

    private void escribirPrimerNumero(){
        try {
            lineaActual = 0; columnaActual = (cantidadNumerosPorLado/2);
            accesoArchivo.seek((long) cantidadBytesPorEntero * columnaActual);
            accesoArchivo.writeInt(numeroMinimo);
            numeroActual = numeroMinimo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void escribirTodasLasLineasCon0() {
        cantidadBytesPorLinea = cantidadBytesPorEntero * cantidadNumerosPorLado;
        posicion=0;
        try {
            for(int linea = 0; linea < cantidadNumerosPorLado; linea++){
                for(int columna = 0; columna < cantidadNumerosPorLado; columna++){
                    /*posicion = ((long) cantidadBytesPorLinea *linea)
                            + ((long) cantidadBytesPorEntero *columna) + ((long)saltosDeLinea*2);*/
                    accesoArchivo.seek(posicion);
                    accesoArchivo.writeInt(0);
                    posicion = posicion+4;
                }
                accesoArchivo.seek(posicion);
                accesoArchivo.writeChars("\r\n");
                posicion = posicion+2;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarArchivoEnGrid() throws IOException {
        Font fuenteUsada;
        if(cantidadNumerosPorLado<=7){ fuenteUsada = interfaz.fuenteGrande;}
        else if (cantidadNumerosPorLado<=13) {fuenteUsada = interfaz.fuenteMediana;}
        else if(cantidadNumerosPorLado<=20){fuenteUsada = interfaz.fuentePequena;}
        else{fuenteUsada = interfaz.fuenteMuyPequena;}
        interfaz.grdCuadrados = new GridPane();
        posicion = 0;
        for(int linea=0; linea<cantidadNumerosPorLado; linea++){
            for (int columna=0; columna<cantidadNumerosPorLado; columna++){
                accesoArchivo.seek(posicion);
                String numeroAMostrar = accesoArchivo.readInt() + "";
                Label lblActual = new Label(numeroAMostrar);
                lblActual.setFont(fuenteUsada);
                interfaz.getGrdCuadrados().add(lblActual, columna, linea);
                posicion = posicion+4;
            }
            posicion = posicion+2;
        }
        interfaz.getGrdCuadrados().setHgap(10);
        interfaz.getGrdCuadrados().setVgap(10);
        interfaz.getLblGrdCuadrados().setGraphic(interfaz.getGrdCuadrados());
        modificarMenuDerecha();
    }

    private void modificarMenuDerecha(){
        double media = ((cantidadNumerosPorLado*cantidadNumerosPorLado) / 2.0d) + 0.5;
        media = numeroMinimo-1 + media;
        double sumaMagica = media * cantidadNumerosPorLado;
        if(numeroLimite == 0){ sumaMagica=0; }
        interfaz.getLblMenuSuma().setText(sumaMagica+"");
        interfaz.getLblMenuNumMinimo().setText(numeroMinimo+"");
        interfaz.getLblMenuNumMaximo().setText(numeroLimite+"");
    }

    private void mensajeErrorFormato(){
        interfaz.getLblHudMensajeArriba().setText("Formato no válido, escriba los números especificados abajo y vuelva a intentarlo.");
    }
    private void mensajeErrorNumeros(){
        interfaz.getLblHudMensajeArriba().setText("Los numeros escritos no están dentro de los limites, vuelva a intentarlo escribiendo bien los numeros especificados.");
    }
    private void mensajeExito(){
        interfaz.getLblHudMensajeArriba().setText("Bien hecho, puede hacer otro cuadro mágico volviendo a escribir otros numeros dentro de los limites y clickando el botón.");
    }

    //SETTERS y GETTERS
    public CuadroMagicoUI getInterfaz() {
        return interfaz;
    }
    public void setInterfaz(CuadroMagicoUI interfaz) {
        this.interfaz = interfaz;
    }
    public int getCantidadNumerosPorLado() {
        return cantidadNumerosPorLado;
    }
    public void setCantidadNumerosPorLado(int cantidadNumerosPorLado) {
        this.cantidadNumerosPorLado = cantidadNumerosPorLado;
    }
}
