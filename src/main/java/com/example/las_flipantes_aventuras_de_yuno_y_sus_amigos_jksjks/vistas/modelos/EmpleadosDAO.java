package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private int id_empleado;
    private String nombre;
    private String rfc;
    private float salario;
    private String telefono;
    private String direccion;

    public void INSERTAR(){
        String query = "INSERT INTO Empleado(nombre, rfc, salario, telefono, direccion) "+
                "VALUES('"+nombre+"','"+rfc+"',"+salario+",'"+telefono+"','"+direccion+"')";
        try {
            Statement stmt =  Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){ e.printStackTrace(); }
    }
    public void ACTUALIZAR(){
        String query = "UPDATE Empleado SET nombre ='"+nombre+"', rfc = '"+rfc+"', salario = "+salario+", telefono = '"+telefono+"', direccion = '"+direccion+"'" +
                "WHERE id_empleado = "+id_empleado;
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){ e.printStackTrace();}
    }
    public void ELIMINAR(){
        String query = "DELETE FROM Empleado WHERE id_empleado ="+id_empleado;
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){ e.printStackTrace();}
    }
    public ObservableList<EmpleadosDAO> CONSULTAR(){
        ObservableList<EmpleadosDAO> listaEmp = FXCollections.observableArrayList();

        String query = "SELECT * FROM empleado";
        try {
            EmpleadosDAO objEmp;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objEmp = new EmpleadosDAO();
                objEmp.id_empleado = res.getInt("id_empleado");
                objEmp.nombre = res.getString("nombre");
                objEmp.rfc = res.getString("rfc");
                objEmp.salario = res.getFloat("salario");
                objEmp.telefono = res.getString("telefono");
                objEmp.direccion = res.getString("direccion");
                listaEmp.add(objEmp);
            }
        }
        catch (Exception e){e.printStackTrace();}

        return listaEmp;
    }
}
