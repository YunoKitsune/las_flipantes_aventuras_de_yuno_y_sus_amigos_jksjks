package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.EmpleadosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadoTaqueria extends Stage {
    Scene escena;
    private VBox vPrincipal;
    private ToolBar tlbMenu;
    private TableView<EmpleadosDAO> tbvEmpleados;
    private Button btnAgregarEmpleado;

    public EmpleadoTaqueria(){
        crearUI();
        this.setTitle("Taqueria Los Inges ");
        this.setScene(escena);
        this.show();
    }

    private void crearUI(){
        String url = "/img/iconoEmpleado.png";
        ImageView imvEmpleado = new ImageView(
                getClass().getResource(url).toString()
        );
        imvEmpleado.setFitHeight(50);
        imvEmpleado.setFitWidth(50);
        btnAgregarEmpleado = new Button();
        btnAgregarEmpleado.setGraphic(imvEmpleado);
        tlbMenu = new ToolBar(btnAgregarEmpleado);

        crearTableView();
        vPrincipal = new VBox(tlbMenu, tbvEmpleados);
        escena = new Scene(vPrincipal, 300, 200);
    }

    private void crearTableView(){
        EmpleadosDAO objEmp = new EmpleadosDAO();

        tbvEmpleados = new TableView<EmpleadosDAO>();                  //titulo de la columna. no el nombre del atributo de EmpleadosDao
        TableColumn<EmpleadosDAO, String> tbcNombre = new TableColumn<>("Empleado");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<EmpleadosDAO, String> tbcRfc = new TableColumn<>("RFC");
        tbcRfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));

        TableColumn<EmpleadosDAO, Float> tbcSueldo = new TableColumn<>("Salario");
        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("salario"));

        TableColumn<EmpleadosDAO, String> tbcTelefono = new TableColumn<>("Telefono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<EmpleadosDAO, String> tbcDireccion = new TableColumn<>("Direccion");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tbvEmpleados.getColumns().addAll(tbcNombre, tbcRfc, tbcSueldo, tbcTelefono, tbcDireccion);
        tbvEmpleados.setItems(objEmp.CONSULTAR());
    }
}
