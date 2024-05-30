package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes.HiloImpresion;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.time.LocalDateTime;

public class Documento_impresion { //Clase para controlar los documentos de manera no tan compleja y tenerlos almacenados en cola.
    private int paginas, noArchivo;
    private String nombre, horaAcceso;
    private final LocalDateTime horaAccesoLDT;
    private Documento_impresion documentoSiguiente;
    private HiloImpresion hiloImpresion;
    private ProgressBar pgbImpresion;
    private Label lblNombre;
    private final String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
    "Septiembre", "Octubre", "Noviembre", "Diciembre"}; //Esto es para poner en letra los meses

    public Documento_impresion(String extension){ //Constructor para formar el archivo
        noArchivo = 1;
        //Crea la hora de acceso
        horaAccesoLDT = LocalDateTime.now();

        //Crea la hora de acceso
        horaAcceso = horaAccesoLDT.getDayOfMonth()+"-"+ horaAccesoLDT.getMonthValue()+"-" +
                horaAccesoLDT.getYear()+"_"+ horaAccesoLDT.getHour()+":"+ horaAccesoLDT.getMinute()+":" +
                horaAccesoLDT.getSecond()+":"+ horaAccesoLDT.getNano()%10000000;

        //Le pone el nombre al documento con la hora de acceso y una extension que recibimos como paràmetro
        nombre = horaAcceso+"."+extension;

        //Pone paginas aleatoriamente
        paginas = (int)(Math.random() * 20 + 1);

    }

    //Setters y getters
    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getNoArchivo() {
        return noArchivo;
    }

    public void setNoArchivo(int noArchivo) {
        this.noArchivo = noArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Documento_impresion getDocumentoSiguiente() {
        return documentoSiguiente;
    }

    public void setDocumentoSiguiente(Documento_impresion documentoSiguiente) {
        this.documentoSiguiente = documentoSiguiente;
    }

    public String getHoraAcceso() {
        return horaAcceso;
    }

    public void setHoraAcceso(String horaAcceso) {
        this.horaAcceso = horaAcceso;
    }

    public HiloImpresion getHiloImpresion() {
        return hiloImpresion;
    }
    public void setHiloImpresion(HiloImpresion hiloImpresion) {
        this.hiloImpresion = hiloImpresion;
    }
    public ProgressBar getPgbImpresion() {
        return pgbImpresion;
    }
    public void setPgbImpresion(ProgressBar pgbImpresion) {
        this.pgbImpresion = pgbImpresion;
    }

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
    }
}
