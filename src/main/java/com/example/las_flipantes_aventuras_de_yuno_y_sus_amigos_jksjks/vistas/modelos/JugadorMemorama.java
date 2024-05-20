package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;


public class JugadorMemorama {
    private JugadorMemorama jugadorSiguiente;
    private int puntos;
    private String strPuntos, nombreJugador;

    public JugadorMemorama(){
        puntos = 0;
        strPuntos = puntos + " puntos";
    }
    public JugadorMemorama(String nombreJugador){
        puntos = 0;
        strPuntos = puntos + " puntos";
        this.nombreJugador = nombreJugador;
    }

    public JugadorMemorama getJugadorSiguiente() {
        return jugadorSiguiente;
    }
    public void setJugadorSiguiente(JugadorMemorama jugadorSiguiente) {
        this.jugadorSiguiente = jugadorSiguiente;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public String getStrPuntos() {
        return strPuntos;
    }
    public void setStrPuntos(String strPuntos) {
        this.strPuntos = strPuntos;
    }
    public String getNombreJugador() {
        return nombreJugador;
    }
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
}
