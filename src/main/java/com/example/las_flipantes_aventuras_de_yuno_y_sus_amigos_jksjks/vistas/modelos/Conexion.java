package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "taqueria";
    static private String USER = "adminTacos";
    static private String PWD  = "123";
    static public Connection connection;

    public static void crearConexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/"+DB,USER,PWD);
            System.out.println("Conexion establecida uwur");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
