package com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas;

import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.Carta;
import com.example.las_flipantes_aventuras_de_yuno_y_sus_amigos_jksjks.vistas.modelos.JugadorMemorama;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.*;

public class Memorama extends Stage {
    public Scene escena;
    public Panel pnlPrincipal;
    public HBox hOpciones;
    public HBox hJ1, hJ2;
    public VBox vDerecha;
    public GridPane gridCartas;
    public Label lblNoPares, lblTimer, lblGridCartas, lblJ1, lblJ2, lblParesJ1, lblParesJ2, lblMensajeDerecha;
    public TextField txtFldPares;
    public Button btnPrevisualizar, btnJugar;
    public Button[][] btnCarta;
    public List<Integer> numAleatorios;
    public Timer tiempo;
    public TimerTask tarea;
    public int anchoCarta, altoCarta, filas, columnas, s, numPares, numParesRevelados;
    public String url, strTiempo, urlActual, msjDerecha;
    private String[] urlCartas;
    private boolean cartaEscogida, ambasCartasVolteadas, clickadoBotonJuego, msjInicial;
    private Carta[][] cartas;
    private Carta cartaEscogida1, cartaEscogida2;
    private JugadorMemorama jugadorActual;
    private Text txtParesJ1, txtParesJ2;

    public void crearYMostrarEscena(){
        escena = new Scene(pnlPrincipal, 1200, 790);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setScene(escena);
        this.setTitle("Memorama");
        this.show();
    }


    public Memorama(){ //Aqui empieza a ejecutarse
        crearUI(); //Hace todo lo del programa
        crearYMostrarEscena(); //Muestra el programa
    }
    public void crearUI(){ //Crea toda la venta
        crearEspacioUI();
        crearUIFueraDelTablero();
        crearUIJuego();
        juntarUIs();
    }
    public void crearEspacioUI(){ //Crea el espacio de la interfaz
        hOpciones = new HBox();
        pnlPrincipal = new Panel();
    }
    public void juntarUIs(){ //Junta las interfaces ya creadas abajo de este método
        pnlPrincipal.setHeading(hOpciones);
        pnlPrincipal.setRight(vDerecha);
        pnlPrincipal.setCenter(lblGridCartas);
        pnlPrincipal.setPadding(new Insets(3));
    }
    public void crearUIFueraDelTablero(){ //Crea todas las cosas que irán arriba y a la derecha
        lblNoPares = new Label("No. pares:");
        lblNoPares.setPrefSize(125, 30);
        lblNoPares.setFont(new Font("Arial", 22));
        lblNoPares.setAlignment(Pos.BOTTOM_CENTER);
        //hLblPares = new HBox(lblNoPares);

        txtFldPares = new TextField("0");
        txtFldPares.setPrefSize(200, 30);
        txtFldPares.setStyle("-fx-font-size: 22px;");

        lblGridCartas = new Label();
        lblMensajeDerecha = new Label("Escriba el numero \n" +
                "de pares y presione \n" +
                "\"Jugar\" para \n" +
                "comenzar una partida.\n\n" +
                "Solo se aceptan \n" +
                "entre 3 y 16 pares\n\n" +
                "Si hay un 0 escrito\n" +
                "se supondrá que son\n" +
                "16 pares.\n\n" +
                "Presione \"Previsualizar\" \n" +
                "para ver cuántas cartas \n" +
                "habrá dependiendo del \n" +
                "número escrito en la caja\n" +
                "de texto.");
        lblMensajeDerecha.setFont(new Font("Arial", 20));
        lblMensajeDerecha.setPadding(new Insets(6));
        lblMensajeDerecha.getStyleClass().add("lbl-info");

        btnPrevisualizar = new Button("Previsualizar");
        btnPrevisualizar.setPrefSize(200, 30);
        btnPrevisualizar.setStyle("-fx-font-size: 22px;");
        btnPrevisualizar.setOnAction(event -> {
            try {
                numPares = Integer.parseInt(txtFldPares.getText()); }
            catch (Exception e){
                msjDerecha = "El caracter que ha escrito \nes invalido \n\nEscriba un número entre \n3 y 16 o 0 y presione \nPrevisualizar o Jugar";
                lblMensajeDerecha.setText(msjDerecha);
                lblMensajeDerecha.getStyleClass().clear();
                lblMensajeDerecha.getStyleClass().add("lbl-warning");
                return;}
            if(numPares == 0){
                crearGridCartas(16);  }
            else{  crearGridCartas( Integer.parseInt(txtFldPares.getText()) );  }
            }
        );

        clickadoBotonJuego=false;
        btnJugar = new Button("Jugar");
        btnJugar.setPrefSize(200, 30);
        btnJugar.setStyle("-fx-font-size: 22px;");
        btnJugar.setOnAction(event -> {
            iniciarPartida();}
        );

        strTiempo = "00:10";
        lblTimer = new Label(strTiempo);
        lblTimer.prefHeight(30);
        lblTimer.setPadding(new Insets(5));
        lblTimer.setFont(new Font("Arial", 22));
        lblTimer.setAlignment(Pos.CENTER_RIGHT);
        lblTimer.getStyleClass().add("lbl-info");

        hOpciones.getChildren().addAll(lblNoPares, txtFldPares, btnPrevisualizar, btnJugar, lblTimer);
        hOpciones.setPrefSize(1200, 70);
        hOpciones.setSpacing(20);
        hOpciones.setAlignment(Pos.CENTER);
    }

    public void crearUIJugadores(){ //Crea los jugadores y los labels de los jugadores.
        Insets padding = new Insets(3);
        JugadorMemorama jugador1 = new JugadorMemorama("Jugador 1");
        JugadorMemorama jugador2 = new JugadorMemorama("Jugador 2");
        jugador1.setJugadorSiguiente(jugador2);
        jugador2.setJugadorSiguiente(jugador1);
        jugadorActual = jugador1;

        lblJ1 = new Label();
        Text txtJ1 = new Text(jugador1.getNombreJugador()+":");
        txtJ1.setFont(new Font("Arial", 20));
        lblJ1.setGraphic(txtJ1); lblJ1.setPadding(padding);
        lblJ1.getStyleClass().add("lbl-success");

        txtParesJ1 = new Text(jugador1.getStrPuntos());
        txtParesJ1.setFont(new Font("Arial", 20));
        lblParesJ1 = new Label(); //añadir funcion para contar pares del jugador 1
        lblParesJ1.setGraphic(txtParesJ1); lblParesJ1.setPadding(padding);
        lblParesJ1.getStyleClass().add("lbl-success");

        hJ1 = new HBox(lblJ1, lblParesJ1);
        hJ1.setSpacing(5);

        lblJ2 = new Label();
        Text txtJ2 = new Text(jugador2.getNombreJugador()+":");
        txtJ2.setFont(new Font("Arial", 20));
        lblJ2.setGraphic(txtJ2); lblJ2.setPadding(padding);
        lblJ2.getStyleClass().add("lbl-danger");

        txtParesJ2 = new Text(jugador2.getStrPuntos());
        txtParesJ2.setFont(new Font("Arial", 20));
        lblParesJ2 = new Label(); //añadir funcion para contar pares del jugador 1
        lblParesJ2.setGraphic(txtParesJ2); lblParesJ2.setPadding(padding);
        lblParesJ2.getStyleClass().add("lbl-danger");

        hJ2 = new HBox(lblJ2, lblParesJ2);
        hJ2.setSpacing(5);

        vDerecha = new VBox(hJ1, hJ2, lblMensajeDerecha); vDerecha.setSpacing(20);
    }

    public void crearUIJuego(){ //Crea todo lo que se usará mientras se estpe jugando.
        msjInicial = true;
        crearGridCartas(16);
        crearUIJugadores();
        msjInicial = false;
    }
    public void crearGridCartas(int numPares){
        //Crea el tablero inicial y cambia el mensaje de la derecha dependiendo del número de pares escritos
        this.numPares = numPares;
        if(  (numPares < 3 || numPares >16) ){
            lblMensajeDerecha.setText("El numero escrito \n" +
                    "está fuera de los \n" +
                    "limites \n\n" +
                    "Escriba un \n" +
                    "número de pares \n" +
                    "entre 3 y 16");
            lblMensajeDerecha.getStyleClass().clear();
            lblMensajeDerecha.getStyleClass().add("lbl-warning");
            return;
        }
        else {
            urlCartas = new String[numPares];
            if (!msjInicial) {
                    msjDerecha = "El número escrito está \n" +
                        "dentro de los limites \n" +
                        "correctos. \n\n";
                if (!clickadoBotonJuego) {
                    msjDerecha += "Escriba otro numero \n" +
                            "entre 3 y 16 o 0 para\n" +
                            "volver a previsualizar\n" +
                            "cuántas cartas habrá. \n\n" +
                            "Presione \"Jugar\" para\n" +
                            "jugar con los pares \n" +
                            "que estén escritos\n" +
                            "en la caja de texto.";
                } else {
                    msjDerecha += "El juego ha comenzado\n\n" +
                            "Jugador actual: \n" +
                            jugadorActual.getNombreJugador();
                }
                lblMensajeDerecha.setText(msjDerecha);
                lblMensajeDerecha.getStyleClass().clear();
                lblMensajeDerecha.getStyleClass().add("lbl-primary");
            }
        }

        Label lblCarta = new Label("Reemplazar");
        lblCarta.setFont(new Font("Arial", 10));

        //Esto reacomoda el numero de filas y columnas dependiendo de cuantos pares habrá
        switch (numPares) {
            case 3, 4, 5 -> {
                filas = 2;
                columnas = numPares;
            }
            case 6, 7, 8, 9 -> {
                filas = 3;
                if(numPares == 6) {
                    columnas = 5;
                }
                else{ columnas = 6; }
            }
            case 10, 11, 12 ->{
                filas = 4;
                columnas = 6;
            }
            case 13, 14, 15, 16 ->{
                filas = 4;
                columnas = 8;
            }
        }

        //Acomoda el tamaño de los botones de las cartas depenndiendo del numero de pares
        if(numPares <= 5){anchoCarta=192; altoCarta=300;}
        else if(numPares <=9){ anchoCarta = 158; altoCarta= 224;}
        else if(numPares <= 12){anchoCarta=150; altoCarta=170;}
        else {anchoCarta=117; altoCarta=170;}
        //Limite donde no deja poner más pares

        //Crea los botones, cartas y grid del memorama
        btnCarta = new Button[filas][columnas];
        cartas = new Carta[filas][columnas];
        gridCartas = new GridPane();
        gridCartas.setPadding(new Insets(2));
        gridCartas.setHgap(6); gridCartas.setVgap(5);

        //Hace un memorama sin desordenar con el numero de pares que tengamos. (Los botones no hacen nada)
        int contadorCartas = 1, contadorPar=1;
        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                if(contadorCartas <= numPares*2){
                    btnCarta[i][j] = new Button();
                    cartas[i][j] = new Carta(i, j);

                    btnCarta[i][j].setPrefSize(anchoCarta,altoCarta);
                    //btnCarta[i][j].setPadding(new Insets(10));
                    url = "/img/"+contadorPar+".jpg";
                    cartas[i][j].setUrl(url);
                    ImageView imvCarta = new ImageView(
                            getClass().getResource(url).toString()
                    );
                    imvCarta.setFitWidth(anchoCarta-20); imvCarta.setFitHeight(altoCarta-20);
                    cartas[i][j].setImvCarta(imvCarta);
                    btnCarta[i][j].setGraphic(cartas[i][j].getImvCarta());
                    urlCartas[contadorPar-1] = url;

                    gridCartas.add(btnCarta[i][j], j, i);
                    contadorCartas++;
                    if(((contadorCartas-1) % 2) == 0){
                        contadorPar++;
                    }
                }
            }
        }
        lblGridCartas.setGraphic(gridCartas);
    }



    private void iniciarPartida(){ //Inicia el juego cuando se presiona Jugar con un número válido
        try{
            numPares = Integer.parseInt(txtFldPares.getText());}
        catch (Exception e){
            msjDerecha = "El caracter que ha escrito \nes invalido \n\nEscriba un número entre \n3 y 16 o 0 y presione \nPrevisualizar o Jugar";
            lblMensajeDerecha.setText(msjDerecha);
            lblMensajeDerecha.getStyleClass().clear();
            lblMensajeDerecha.getStyleClass().add("lbl-warning");
            return;
        }

        //Inicio variables principales del juego
        numParesRevelados = 0;
        ambasCartasVolteadas=false;
        if(numPares == 0 ){ numPares = 16;}
        if((numPares >= 3 && numPares <= 16)) {
            clickadoBotonJuego = true; //Uso esto como bandera para diferenciar funciones entre "Jugar" y "Previsualizar"
        }
        crearGridCartas( numPares );

        //Aqui es donde realmente inicia el juego despues de poner las condiciones iniciales
        if((numPares >= 3 && numPares <= 16)) {
            btnJugar.setDisable(true);
            btnPrevisualizar.setDisable(true);
            clickadoBotonJuego = true; //Uso esto como bandera para diferenciar funciones entre "Jugar" y "Previsualizar"
            revolverCartas();
            ocultarTodasLasCartas();
            iniciarCronometro();
        }
    }
    private void revolverCartas(){
        //Primero hace una lista contando cuántas cartas hay en el tablero, luego randomiza esa lista de números consecutivos
        //Al final los usa ya randomizados para poner las cartas en el orden de la lista de numeros randomizados
        int contadorPares= 1; int bandera = 1;
        numAleatorios = new ArrayList<>();
        while(contadorPares <= numPares){
            numAleatorios.add(contadorPares);
            bandera++;
            if(bandera == 3){
                contadorPares++;
                bandera=1;
            }
        }
        Collections.shuffle(numAleatorios);
        ponerCartasRevueltas(); //Este método usa la lista de números randomizados
    }
    private void ponerCartasRevueltas(){
        //Usa una lista de números para acomodar el orden de las cartas en el grid (en forma de botones)
        btnCarta = new Button[filas][columnas];
        //El arreglo de cartas es para saber siempre qué imagen de qué cartas está en qué posición de i y j
        cartas = new Carta[filas][columnas];

        int contadorCartas = 1;
        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                if(contadorCartas <= numPares*2) {
                    btnCarta[i][j] = new Button();
                    cartas[i][j] = new Carta(i, j); //Con esto sabremos en todo momento que posicion tiene cada carta

                    //Esta parte es para darle la imagen al boton de la carta en la posicion actual de i y j
                    btnCarta[i][j].setPrefSize(anchoCarta, altoCarta);
                    url = "/img/" + numAleatorios.get(contadorCartas - 1) + ".jpg";
                    cartas[i][j].setUrl(url);
                    ImageView imvCarta = new ImageView(
                            getClass().getResource(url).toString()
                    );
                    imvCarta.setFitWidth(anchoCarta - 20);
                    imvCarta.setFitHeight(altoCarta - 20);
                    cartas[i][j].setImvCarta(imvCarta);
                    btnCarta[i][j].setGraphic(cartas[i][j].getImvCarta());

                    /*Con esto hacemos que el boton muestre la imagen de la carta dependiendo de la posicion del boton
                    y validar que sea la primera o segunda carta revelada y que si es la segunda, que sea o no una con la misma imagen*/
                    int finalI = i;
                    int finalJ = j;
                    btnCarta[i][j].setOnAction((event) -> {
                        if(!ambasCartasVolteadas && !cartas[finalI][finalJ].haSidoClickada){
                            btnCarta[finalI][finalJ].setGraphic( cartas[finalI][finalJ].getImvCarta() );
                            comprobarCarta(cartas[finalI][finalJ].getUrl(), finalI, finalJ);
                        }
                    });
                    //Y ya al final acomodamos el boton creado a la posocion actual en el grid
                    gridCartas.add(btnCarta[i][j], j, i);
                    contadorCartas++;
                }
            }
        }
    }
    private void ocultarTodasLasCartas(){ //Esto reemplaza todas las imagenes de los botones por una imagen repetida
        int contadorCartas = 1;
        url = "/img/0.jpg";
        for (int i=0; i<filas; i++){
            for (int j=0; j<columnas; j++){
                if(contadorCartas <= numPares*2) {
                ImageView imagenReverso = new ImageView(
                        getClass().getResource(url).toString()
                );
                imagenReverso.setFitWidth(anchoCarta - 20);
                imagenReverso.setFitHeight(altoCarta - 20);
                btnCarta[i][j].setGraphic(imagenReverso);
                contadorCartas++;
                }
            }
        }
    }



    private void comprobarCarta(String urlCartaEscogida, int i, int j){
        //El metodo completo valida si es la primer o segunda carta escogida y si son iguales o diferentes

        if(cartaEscogida){ //Aqui valida si ya hay una carta escogida antes, si la hay:
            ambasCartasVolteadas = true;
            cartaEscogida2 = cartas[i][j];
            cartaEscogida2.haSidoClickada = true;
            //Valida si ambas son iguales o diferentes
            if (urlCartaEscogida.equals(urlActual)) { //Si son iguales:
                msjDerecha = "Carta correcta\n\n";
                btnCarta[cartaEscogida1.getI()][cartaEscogida1.getJ()].setOnAction((event) -> {}); //Desactiva todas las funciones
                btnCarta[cartaEscogida2.getI()][cartaEscogida2.getJ()].setOnAction((event) -> {});

                //Si son iguales, el jugador actual obtiene un punto y actualiza los labels
                jugadorActual.setPuntos(jugadorActual.getPuntos() + 1);
                if(jugadorActual.getPuntos() == 1){ jugadorActual.setStrPuntos(jugadorActual.getPuntos()+" punto"); }
                else{ jugadorActual.setStrPuntos(jugadorActual.getPuntos()+" puntos"); }
                //Cambia el label que pertenezca al jugador que obtuvo el punto
                if(jugadorActual.getNombreJugador().equals("Jugador 1")){ txtParesJ1.setText(jugadorActual.getStrPuntos()); }
                else{ txtParesJ2.setText(jugadorActual.getStrPuntos()); }

                reiniciarTurno();
                numParesRevelados++;
            } else {
                //Si las cartas son diferentes, reesconde ambas cartas y pasa el turno al siguiente jugador
                msjDerecha = "Carta incorrecta\n\n";
                cartaEscogida1.haSidoClickada = false; cartaEscogida2.haSidoClickada= false;
                reesconderCartas( cartaEscogida1.getI(), cartaEscogida1.getJ(), cartaEscogida2.getI(), cartaEscogida2.getJ() );
                jugadorActual = jugadorActual.getJugadorSiguiente();
            }

            //Miantras nadie haya ganado, continúa el juego con el siguiente jugador y reinicia el cronometro
            if(numParesRevelados != numPares){
                msjDerecha += "Jugador actual:\n" + jugadorActual.getNombreJugador();
                lblMensajeDerecha.setText(msjDerecha);
                iniciarCronometro();
            }
            else{ //Si alguien ya ganó:
                String msjFinal;

                //Valida si hay un empate o si alguien ganó y hace un mensaje de esto.
                if(jugadorActual.getPuntos() == jugadorActual.getJugadorSiguiente().getPuntos()){
                    msjFinal = "Ambos jugadores han empatado, que emocionante fue >w<";
                }
                else{
                    msjFinal = "El ";
                    if(jugadorActual.getPuntos() > jugadorActual.getJugadorSiguiente().getPuntos()){
                        msjFinal += jugadorActual.getNombreJugador();
                    }
                    else{
                        msjFinal += jugadorActual.getJugadorSiguiente().getNombreJugador();
                        jugadorActual = jugadorActual.getJugadorSiguiente();
                    }
                    msjFinal += " ganó por "+ jugadorActual.getPuntos() + " puntos ^w^/";
                }
                //Hace una alerta con el mensaje puuesto y reinicia todo el juego.
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,msjFinal);
                alert = new Alert(Alert.AlertType.CONFIRMATION, msjFinal, ButtonType.FINISH);
                alert.showAndWait();
                reiniciarTodo();
                return;
            }
        }
        else{ // Si apenas escoció la primer carta:
            /*Guardamos la carta para validarla con la segunda carta y le decimos que ya ha sido clickada para que
            no pase nada en caso de que vuelva a presionar el mismo boton de la carta que ya reveló*/
            urlActual = urlCartaEscogida;
            cartaEscogida1 = cartas[i][j];
            cartaEscogida1.haSidoClickada = true;
            cartaEscogida = true;
        }
    }
    private void reesconderCartas(int i1, int j1, int i2, int j2){
        //Solo vuelve a reesconder cartas en 1 segundo.
        Timer segundo = new Timer();
        url = "/img/0.jpg";
        ImageView imagenReverso1 = new ImageView(
                getClass().getResource(url).toString()
        );
        imagenReverso1.setFitWidth(anchoCarta - 20);
        imagenReverso1.setFitHeight(altoCarta - 20);

        ImageView imagenReverso2 = new ImageView(
                getClass().getResource(url).toString()
        );
        imagenReverso2.setFitWidth(anchoCarta - 20);
        imagenReverso2.setFitHeight(altoCarta - 20);
        TimerTask tareaOcultarCarta = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    btnCarta[i1][j1].setGraphic(imagenReverso1);
                    btnCarta[i2][j2].setGraphic(imagenReverso2);
                    reiniciarTurno();
                });
                segundo.cancel();
            }
        };
        segundo.schedule(tareaOcultarCarta, 1000);
        cartaEscogida1 = null; cartaEscogida2 = null;
    }
    private void reiniciarTurno(){ //Solo reinicia las banderas que dicen si ya se escogió una o dos cartas
        cartaEscogida = false;
        ambasCartasVolteadas = false;
    }

    private void pasarTurnoAlTerminarTiempo(){ //En caso que se termine el tiempo
        //reinicia las variables del juego
        cartaEscogida = false;
        ambasCartasVolteadas = false;
        //Tambien reesconde la carta escogida en caso de que haya escogido una carta en el turno.
        if(cartaEscogida1 != null){
            url = "/img/0.jpg";
            ImageView imagenReverso = new ImageView(
                    getClass().getResource(url).toString()
            );
            imagenReverso.setFitWidth(anchoCarta - 20);
            imagenReverso.setFitHeight(altoCarta - 20);
            btnCarta[cartaEscogida1.getI()][cartaEscogida1.getJ()].setGraphic(imagenReverso);
            cartaEscogida1.haSidoClickada = false;
            cartaEscogida1 = null;
            cartaEscogida = false;
        }
        //Y pasa el turno al jugador que sigue
        jugadorActual = jugadorActual.getJugadorSiguiente();
        msjDerecha = "Tiempo terminado.\n\n"+"Jugador actual:\n" + jugadorActual.getNombreJugador();
        lblMensajeDerecha.setText(msjDerecha);
    }


    public void iniciarCronometro(){
        //Inicia el timer para que cada turno tenga 10 segundos y va bajando a 0 cada segundo
        if(tiempo != null){
            tiempo.cancel(); tarea.cancel();}

        tiempo = new Timer(); s=11;
        tarea = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> escribirTiempoCadaSegundo());
            }
        };
        tiempo.schedule(tarea, 0, 1000);
    }
    private void escribirTiempoCadaSegundo(){ //Actualiza el tiempo mostrado en el label del tiempo cada segundo
        s--;
        if(s<0){
            s=10;
            pasarTurnoAlTerminarTiempo(); //Cuando se temmina el tiempo, pasa el turno al otro jugador.
        }
        strTiempo = "00:" + (s<=9? "0":"") + s;
        lblTimer.setText(strTiempo);
    }


    private void reiniciarTodo(){ //Reinicia el juego entero. (Solo usar cuando la partida termine)
        tiempo.cancel(); tarea.cancel(); tiempo = null; tarea= null;
         crearUI(); crearYMostrarEscena();

    }

}
