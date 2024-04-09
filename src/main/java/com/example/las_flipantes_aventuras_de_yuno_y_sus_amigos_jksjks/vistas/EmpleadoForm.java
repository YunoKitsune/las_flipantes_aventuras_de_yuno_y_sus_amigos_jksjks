package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.EmpleadosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadoForm extends Stage {
    private TableView<EmpleadosDAO> tbvEmpleados;
    private EmpleadosDAO empleadosDAO;
    String[] arPromts = {"Nombre del empleado","RFC del empleado","Sueldo del empleado", "Telefono del empleado", "Direccion del empleado"};
    private Scene escena;
    private TextField[] arTxtCampo = new TextField[5];
    private Button btnGuardar;
    private VBox vPrincipal;


    public EmpleadoForm(TableView<EmpleadosDAO> tbvEmpleados, EmpleadosDAO empleadosDAO){
        this.tbvEmpleados = tbvEmpleados;
        this.empleadosDAO = (empleadosDAO == null) ? new EmpleadosDAO() : empleadosDAO;
        crearUI();
        this.setTitle("Insertar usuario");
        this.setScene(escena);
        this.show();
    }

    public void crearUI(){
        vPrincipal = new VBox();
        vPrincipal.setSpacing(10);
        vPrincipal.setAlignment(Pos.CENTER);
        vPrincipal.setPadding(new Insets(10));
        for(int i =0; i<arTxtCampo.length; i++){
            arTxtCampo[i] = new TextField();
            arTxtCampo[i].setPromptText(arPromts[i]);
            vPrincipal.getChildren().add(arTxtCampo[i]);
        }
        llenarForm();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarEmpleado());
        //btnGuardar.setAlignment(Pos.CENTER);
        vPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vPrincipal, 300, 230);
    }
    public void llenarForm(){
        arTxtCampo[0].setText(empleadosDAO.getNombre());
        arTxtCampo[1].setText(empleadosDAO.getRfc());
        arTxtCampo[2].setText(empleadosDAO.getSalario()+"");
        arTxtCampo[3].setText(empleadosDAO.getTelefono());
        arTxtCampo[4].setText(empleadosDAO.getDireccion());
    }

    public void guardarEmpleado(){
        //Manda los datos escritos al empleadosDAO y este lo manda a la base de datos
        empleadosDAO.setNombre(arTxtCampo[0].getText());
        empleadosDAO.setRfc(arTxtCampo[1].getText());
        empleadosDAO.setSalario(Float.parseFloat(arTxtCampo[2].getText()));
        empleadosDAO.setTelefono(arTxtCampo[3].getText());
        empleadosDAO.setDireccion(arTxtCampo[4].getText());
        if(empleadosDAO.getId_empleado() > 0){
            empleadosDAO.ACTUALIZAR();
        }
        else {
            empleadosDAO.INSERTAR();
        }

        //Cuando la base de datos ya tiene los nuevos datos registrados, los pone en la TableView tbvEmpleados
        tbvEmpleados.setItems(empleadosDAO.CONSULTAR());
        tbvEmpleados.refresh();

        arTxtCampo[0].clear();
        arTxtCampo[1].clear();
        arTxtCampo[2].clear();
        arTxtCampo[3].clear();
        arTxtCampo[4].clear();
    }
}
