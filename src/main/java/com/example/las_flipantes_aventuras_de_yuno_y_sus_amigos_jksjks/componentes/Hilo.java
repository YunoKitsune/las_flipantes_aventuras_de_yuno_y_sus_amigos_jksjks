package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread{
    private ProgressBar pgbCarril;
    public Hilo(String name){
        super(name);
    }

    @Override
    public void run() {
        //super.run();
        double avance = 0;
        while(avance <= 1){
            //System.out.println("Km "+i + " llegó " + getName());
            avance += Math.random()/10;
            pgbCarril.setProgress(avance);
            try{
                Thread.sleep((long)(Math.random()*3000));
            }
            catch(Exception e){}
        }
    }

    public ProgressBar getPgbCarril() {
        return pgbCarril;
    }

    public void setPgbCarril(ProgressBar pgbCarril) {
        this.pgbCarril = pgbCarril;
    }
}
