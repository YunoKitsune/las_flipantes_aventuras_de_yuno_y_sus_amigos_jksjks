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

    public Calculadora(){ //Poner escena
        crearUI();
        this.setTitle("Mi SEGUNDA calculadora ^w^(?)");
        this.setScene(escena);
        this.show();
    }

    private void crearUI(){ //crear componentes visuales
        txtPantalla = new TextField("0");
        txtPantalla.setEditable(false);
        gdpTeclado = new GridPane();
        crearTeclado();
        vContenedor = new VBox(txtPantalla,gdpTeclado);
        vContenedor.setSpacing(10);
        escena = new Scene(vContenedor, 200, 200);
        escena.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
    }

    private void crearTeclado(){ //Crear grid de teclado
        int pos=0; char simbolo;
        for(int i=0; i<4 ; i++){
            for(int j=0; j<4; j++){
                simbolo = arEtiquetas[pos];
                char simboloF = simbolo; //Esto es porque Java a fuerzas pide otra variable temporal

                artBotones[i][j] = new Button(simboloF+"");
                artBotones[i][j].setPrefSize(50,50);
                artBotones[i][j].setOnAction(event -> presionarTecla(simboloF));

                gdpTeclado.add(artBotones[i][j],j,i);
                pos++;
            }
        }
    }

    public void presionarTecla(char simbolo){ //Acciones cuando presione tecla
        escribir(simbolo);
        funcionSimbolo(simbolo);
    }
    public void escribir(char simbolo){ //Si escribe un signo, lo pone en el textField
        //Aqui van las reglas para escribir
        //Si escribe signos y ya dice "Sintax" o "Math" Error, borra el texto y pone solo el signo
        if(simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/' || simbolo == '.'){
            if(txtPantalla.getText().equals("Sintax Error") || txtPantalla.getText().equals("Math Error")) {
                txtPantalla.setText(simbolo + "");
                //Si no dice eso, solo lo escribe sin borrar lo anterior
            }else{
                txtPantalla.appendText(simbolo + "");
            }
        }
        else{ //Si lo que escribe es un numero:
            if(txtPantalla.getText().equals("0") //En condiciones iniciales borra lo que tenga
                    || txtPantalla.getText().equals("Sintax Error")
                    || txtPantalla.getText().equals("Math Error")){
                txtPantalla.setText(""+simbolo);
            }
            else{ //En condiciones intermedias no borra nada y lo escribe
                txtPantalla.appendText(simbolo+"");
            }
        }
    }
    public void funcionSimbolo(char simbolo){
        //Aqui van las funciones de los simbolos en caso de ser signo, igual, punto o numero
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
        // Al escribir un signo y ya escribió otro, da "Sintax Error"
        if(!txtPantalla.getText().equals(signo+"")) {
            if (!verificarSintaxisSigno()) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }
        } else{
            sintaxError = true;
            textoResultado = "Sintax Error";
        }
        //Si es el primer signo escrito, agarra como primer operando los numeros anteriores
            if (operacionAnterior.equals("") && numEscrito == true) {
                String primerNumeroEscrito = txtPantalla.getText().substring(posInicial, txtPantalla.getLength() - 1);
                resultadoNumerico = Float.parseFloat(primerNumeroEscrito);
        //Si ya hay signos de operacion, hace la operacion del signo que haya guardado antes
            } else if (!operacionAnterior.equals("") && sintaxError == false) {
                operar();
            }
            //El signo escrito para hacer la operacion después
            operacionAnterior = "" + signo;
            numEscrito = false;
    }

    public void funcionPunto() { //Funciones de cuando se escribe un punto
        //Verifica si no hay sintax error de antes o si hay un signo antes
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
            //Verifica si no hay más puntos en la el textField
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
    public void funcionIgual(){ //La función desupes de escribir el signo igual
        //Verifica si ya estaba escrito desde antes "Sintax" o "Math Error"
        if(!txtPantalla.getText().equals("Sintax Error") && !txtPantalla.getText().equals("Math Error") && !txtPantalla.getText().equals("=")) {
            //Verifica si hasta ahora encuentra un error de sintaxis ppr si antes no lo habia
            if (!verificarSintaxisSigno() || txtPantalla.getText().isEmpty()) {
                sintaxError = true;
                textoResultado = "Sintax Error";
            }
        } //Si ya antes habia escrito "Sintax" o "Math" Error, lo deja asi
        else if(txtPantalla.getText().equals("Sintax Error") || txtPantalla.getText().equals("Math Error") || txtPantalla.getText().equals("=")){
            sintaxError = true;
            textoResultado = "Sintax Error";
        }
        //Si no hay error de sintaxis:
        if(!sintaxError){
            //Si solo hay un numero escrito (sin signo) lo deja tal cual
            if(operacionAnterior.isEmpty()){
                String primerNumeroEscrito = txtPantalla.getText().substring(posInicial, txtPantalla.getLength()-1);
                resultadoNumerico = Float.parseFloat(primerNumeroEscrito);
            }
            else{ //Si hay signos, los opera y muestra
                operar();
            }
            txtPantalla.setText(""+resultadoNumerico);
        }
        else{ //Si hay Sintax error escribe "Sintax Error"
            txtPantalla.setText("Sintax Error");
        }
        //Si hay una division entre 0, escribe "Math Error"
        if(txtPantalla.getText().equals("Infinity")){
            txtPantalla.setText("Math Error");
        }
        //Al terminar, reinicia las variables que necesitan ser reiniciadas
        reiniciarTodo();
    }
    public void funcionNumero(){ //Funciones cuando escribe un numero
        //Cambia la posicion que el substring debe tener en cuenta para tomar numeros
        posFinal= txtPantalla.getLength();
        //Si aun no habia numeros escritos, verifca dónde está la primer posicion del numero actual
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
        //Opera los numeros escritos tomando en cuenta el signo antes escrito.
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
    public boolean verificarSintaxisSigno(){ //Verifica si no hay mas de dos signos
        String simboloAnterior = txtPantalla.getText(txtPantalla.getLength()-2, txtPantalla.getLength()-1);
        if(simboloAnterior.equals("+") || simboloAnterior.equals("-") ||
            simboloAnterior.equals("*") || simboloAnterior.equals("/") || simboloAnterior.equals(".")){
            return false;
        } //En caso de que sí haya mas de un signo, entonces manda un true al "Sintax Error"
        else{return true;}
    }
    public void reiniciarTodo(){
        //Reinicia las variables necesarias cuando escribe el signo igual
        sintaxError = false; operacionAnterior=""; numEscrito=false;
        posInicial=0; posFinal=0;
    }
}
