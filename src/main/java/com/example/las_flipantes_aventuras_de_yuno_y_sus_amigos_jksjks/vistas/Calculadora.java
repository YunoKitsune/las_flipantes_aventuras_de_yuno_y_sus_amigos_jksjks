package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {
    private Scene escena;
    private VBox vContenedor;
    private GridPane gdpTeclado;
    private TextField txtPantalla;
    private Button[][] artBotones = new Button[4][4];
    private char[] arEtiquetas = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};
    private int posInicial=0,posFinal=0;
    float resultadoNumerico=0 ;
    String textoResultado="0", operacionAnterior="";
    boolean sintaxError=false, MathError=false, numEscrito=false;

    public Calculadora(){
        crearUI();
        this.setTitle("Mi SEGUNDA calculadora ^w^(?)");
        this.setScene(escena);
        this.show();
    }

    private void crearUI(){
        txtPantalla = new TextField("0");
        txtPantalla.setEditable(false);
        gdpTeclado = new GridPane();
        crearTeclado();
        vContenedor = new VBox(txtPantalla,gdpTeclado);
        vContenedor.setSpacing(10);
        escena = new Scene(vContenedor, 200, 200);
        escena.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
    }

    private void crearTeclado(){
        int pos=0; char simbolo;
        for(int i=0; i<4 ; i++){
            for(int j=0; j<4; j++){
                simbolo = arEtiquetas[pos];
                char simboloF = simbolo; //Esto es porque Java a fuerzas pide otra variable temporal

                artBotones[i][j] = new Button(simboloF+"");
                artBotones[i][j].setPrefSize(50,50);
                artBotones[i][j].setOnAction(event -> presionarTecla(simboloF));

                /*if( arEtiquetas[pos] == '+' || arEtiquetas[pos] == '-' || arEtiquetas[pos] == '*' || arEtiquetas[pos] == '/'){
                    //Operadores
                    artBotones[i][j].setId("color-operador");
                }
                else{
                    if(arEtiquetas[pos] == '.'){
                        //Punto

                    }else if(arEtiquetas[pos] == '='){
                        //Igual

                    }
                    else{ //Números

                    }
                }*/

                gdpTeclado.add(artBotones[i][j],j,i);
                pos++;
            }
        }
    }

    public void presionarTecla(char simbolo){
        escribir(simbolo);
        funcionSimbolo(simbolo);
    }
    public void escribir(char simbolo){ //Si escribe un signo
        if(simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/' || simbolo == '.'){
            if(txtPantalla.getText().equals("Sintax Error") || txtPantalla.getText().equals("Math Error")) {
                txtPantalla.setText(simbolo + "");

            }else{
                txtPantalla.appendText(simbolo + "");
            }
        }
        else{ //Si lo que escribe es un numero:
            if(txtPantalla.getText().equals("0") //En condiciones iniciales
                    || txtPantalla.getText().equals("Sintax Error")
                    || txtPantalla.getText().equals("Math Error")){
                txtPantalla.setText(""+simbolo);
            }
            else{ //En condiciones intermedias
                txtPantalla.appendText(simbolo+"");
            }
        }
    }
    public void funcionSimbolo(char simbolo){

            if (simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/') {
                funcionSigno(simbolo);
            } else if (simbolo == '.') {
                funcionPunto();
            } else if (simbolo == '=') {
                funcionIgual();
            } else {
                funcionNumero();
            }

    }

    public void funcionSigno(char signo){
        if(!txtPantalla.getText().equals(signo+"")) {
            if (!verificarSintaxisSigno()) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }
        } else{
            sintaxError = true;
            textoResultado = "Sintax Error";
        }
            if (operacionAnterior.equals("") && numEscrito == true) {
                String primerNumeroEscrito = txtPantalla.getText().substring(posInicial, txtPantalla.getLength() - 1);
                resultadoNumerico = Float.parseFloat(primerNumeroEscrito);
            } else if (!operacionAnterior.equals("") && sintaxError == false) {
                operar();
            }
            //posInicial= txtPantalla.getLength();
            operacionAnterior = "" + signo;
            numEscrito = false;

    }

    public void funcionPunto() {
        if(!txtPantalla.getText().equals(".")) {
            if (!verificarSintaxisSigno() || txtPantalla.getText().isEmpty()) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }
        }else{ sintaxError = true;
            textoResultado = "Sintax Error";
        }
            int contadorCaracteres = txtPantalla.getLength() - 1;
            int contadorPuntos = 0;
            char posiblePunto;
            while (contadorCaracteres >= 0) {
                posiblePunto = txtPantalla.getText().charAt(contadorCaracteres);
                if (posiblePunto == '.') {
                    contadorPuntos++;
                }
                contadorCaracteres--;
            }
            if (contadorPuntos > 1) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }

    }
    public void funcionIgual(){
        if(!txtPantalla.getText().equals("Sintax Error") && !txtPantalla.getText().equals("Math Error") && !txtPantalla.getText().equals("=")) {
            if (!verificarSintaxisSigno() || txtPantalla.getText().isEmpty()) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }
        }
        else if(txtPantalla.getText().equals("Sintax Error") || txtPantalla.getText().equals("Math Error") || txtPantalla.getText().equals("=")){
            sintaxError = true;
            textoResultado = "Sintax Error";
        }
        if(!sintaxError){
            if(operacionAnterior.isEmpty()){
                String primerNumeroEscrito = txtPantalla.getText().substring(posInicial, txtPantalla.getLength()-1);
                resultadoNumerico = Float.parseFloat(primerNumeroEscrito);
            }
            else{
                operar();
            }
            txtPantalla.setText(""+resultadoNumerico);
        }
        else{
            txtPantalla.setText("Sintax Error");
        }
        /*if(!txtPantalla.getText().equals("Sintax Error") && !txtPantalla.getText().equals("Math Error")){
            txtPantalla.setText(""+resultadoNumerico);
        }*/
        if(txtPantalla.getText().equals("Infinity")){
            txtPantalla.setText("Math Error");
        }
        reiniciarTodo();
    }
    public void funcionNumero(){
        posFinal= txtPantalla.getLength();
        if(!numEscrito){
        posInicial = posFinal;
        while(posInicial>0 && txtPantalla.getText().charAt(posInicial-1)!='+'
                 && txtPantalla.getText().charAt(posInicial-1)!='-'
                 && txtPantalla.getText().charAt(posInicial-1)!='*'
                 && txtPantalla.getText().charAt(posInicial-1)!='/'){
            posInicial--;
            numEscrito=true;
        }}

    }


    public void operar(){
        String segundoNumeroS=txtPantalla.getText().substring(posInicial,posFinal);
        float segundoNumeroF=Float.parseFloat(segundoNumeroS);
        switch (operacionAnterior){
            case "+":
                resultadoNumerico += segundoNumeroF;
                break;
            case "-":
                resultadoNumerico -= segundoNumeroF;
                break;
            case "*":
                resultadoNumerico *= segundoNumeroF;
                break;
            case "/":
                resultadoNumerico /= segundoNumeroF;
                break;
            default:
                break;
        }
    }
    public boolean verificarSintaxisSigno(){
        String simboloAnterior = txtPantalla.getText(txtPantalla.getLength()-2, txtPantalla.getLength()-1);
        if(simboloAnterior.equals("+") || simboloAnterior.equals("-") ||
            simboloAnterior.equals("*") || simboloAnterior.equals("/") || simboloAnterior.equals(".")){
            return false;
        }
        else{return true;}
    }
    public void reiniciarTodo(){
        sintaxError = false; operacionAnterior=""; numEscrito=false;
        posInicial=0; posFinal=0;
    }
}
