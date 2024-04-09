package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.componentes;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.EmpleadoForm;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.EmpleadosDAO;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCell extends TableCell<EmpleadosDAO, String> {
    Button btnCelda;
    int opc;
    EmpleadosDAO objEmp;
    public ButtonCell(int opc){
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> accionBoton(opc));
    }

    //Aqui le ponemos el boton adentro del "TableCell"
    //Para que el button sea compatible con el TableView
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(btnCelda);
            //Si, por alguna razon, el Button se agrega como Graphic del TableCell
        }
    }
    public void accionBoton(int opc){
        TableView<EmpleadosDAO> tbvEmpleados = ButtonCell.this.getTableView();
        objEmp = tbvEmpleados.getItems().get(ButtonCell.this.getIndex());
        if(opc == 1) {
            //Código de Editar
            new EmpleadoForm(tbvEmpleados, objEmp);
        }
        else{
            //Código de Eliminar
            Alert alert  = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("¿Está seguro de que quiere eliminar el registro de "+objEmp.getNombre()+"?");
            alert.setContentText("No se podrá recuperar la informacion a menos que la reinserte");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                objEmp.ELIMINAR();
                tbvEmpleados.setItems(objEmp.CONSULTAR());
                tbvEmpleados.refresh();
                //Eliminar
            }
        }
    }

}

