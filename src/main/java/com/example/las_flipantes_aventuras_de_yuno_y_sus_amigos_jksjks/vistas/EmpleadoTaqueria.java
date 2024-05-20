package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes.ButtonCell;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.EmpleadosDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class EmpleadoTaqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private Scene escena;
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
        btnAgregarEmpleado.setOnAction(event -> new EmpleadoForm(tbvEmpleados, null));
        btnAgregarEmpleado.setGraphic(imvEmpleado);
        tlbMenu = new ToolBar(btnAgregarEmpleado);

        crearTableView();
;
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvEmpleados);
        Label lblR = new Label("Label Random");
        //bpnPrincipal.setTop(lblR);
        btnAgregarEmpleado.getStylesheets().add("");
        pnlPrincipal = new Panel("Taqueria");
        pnlPrincipal.getStyleClass().add("panel-primary");
        //vPrincipal = new VBox(tlbMenu, tbvEmpleados);
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());}

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

        TableColumn<EmpleadosDAO, String> tbcEditar = new TableColumn<EmpleadosDAO, String>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> empleadosDAOStringTableColumn) {
                        return new ButtonCell(1);
                    }
                }
        );

        TableColumn<EmpleadosDAO, String> tbcEliminar = new TableColumn<EmpleadosDAO, String>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> empleadosDAOStringTableColumn) {
                        return new ButtonCell(2);
                    }
                }
        );

        tbvEmpleados.getColumns().addAll(tbcNombre, tbcRfc, tbcSueldo, tbcTelefono, tbcDireccion, tbcEditar, tbcEliminar);
        tbvEmpleados.setItems(objEmp.CONSULTAR());
    }
}
