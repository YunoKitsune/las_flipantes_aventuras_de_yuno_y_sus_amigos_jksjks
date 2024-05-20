package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;

import javafx.scene.image.ImageView;

public class Carta {
    private String url;
    private ImageView imvCarta;
    private int i, j;
    public boolean haSidoClickada;

    public Carta(int i, int j){
        this.i = i;
        this.j = j;
        haSidoClickada = false;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public ImageView getImvCarta() {
        return imvCarta;
    }
    public void setImvCarta(ImageView imvCarta) {
        this.imvCarta = imvCarta;
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public int getJ() {
        return j;
    }
    public void setJ(int j) {
        this.j = j;
    }
}
