module com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks to javafx.fxml;
    exports com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks;

    requires java.sql;
    requires mariadb.java.client;
    opens com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;
}