package Controlador;

import Modelo.Sudoku;
import Vista.SudokuVista;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class SudokuControlador {
    private Sudoku modelo;
    private SudokuVista vista;

    public SudokuControlador(Sudoku modelo, SudokuVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Acciones de los botones
        // Cuando dan clic en los botones de plantilla, cargamos el juego segun la plantilla
        vista.getBtnPlantilla1().setOnAction(evento -> cargarJuego(1));
        vista.getBtnPlantilla2().setOnAction(evento -> cargarJuego(2));
        vista.getBtnPlantilla3().setOnAction(evento -> cargarJuego(3));
        // Cuando dan clic en "Revisar", le preguntamos al modelo si está resuelto
        vista.getBtnValidar().setOnAction(evento -> {
            if (modelo.estaResuelto() == true) {
                // Si regresa verdadero, mandamos alerta de información
                mostrarAlerta(Alert.AlertType.INFORMATION, "¡Felicidades!", "El Sudoku es correcto.");
            } else {
                // Si regresa falso, mandamos alerta de error
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Faltan números o hay errores.");
            }
        });

        TextField[][] casillas = vista.getCasillas();

        // Recorremos las casillas para agregarles el evento de escribir
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField tf = casillas[i][j];
                int fila = i;
                int col = j;

                // Evento 1: Cuando el usuario suelta una tecla después de escribir
                tf.setOnKeyReleased(evento -> {
                    if (modelo.esCasillaFija(fila, col) == true) {
                        return; // No hace nada si es una casilla esta bloqueada
                    }

                    String texto = tf.getText();// Obtenemos lo que escribió
                    int numeroIngresado = 0;
                    boolean esNumero = false;

                    //Se revisa con if si el texto es del 1 al 9
                    if (texto.equals("1")) { numeroIngresado = 1; esNumero = true; }
                    else if (texto.equals("2")) { numeroIngresado = 2; esNumero = true; }
                    else if (texto.equals("3")) { numeroIngresado = 3; esNumero = true; }
                    else if (texto.equals("4")) { numeroIngresado = 4; esNumero = true; }
                    else if (texto.equals("5")) { numeroIngresado = 5; esNumero = true; }
                    else if (texto.equals("6")) { numeroIngresado = 6; esNumero = true; }
                    else if (texto.equals("7")) { numeroIngresado = 7; esNumero = true; }
                    else if (texto.equals("8")) { numeroIngresado = 8; esNumero = true; }
                    else if (texto.equals("9")) { numeroIngresado = 9; esNumero = true; }

                    //Si se escribio un numero valido procedemos a lo siguinte
                    if (esNumero == true) {
                        //Se le pregunta al modelo si se esta cumpliendo las reglas
                        boolean esValido = modelo.esValido(fila, col, numeroIngresado);
                        modelo.setValor(fila, col, numeroIngresado);

                        if (esValido == true) {
                            //Si es correcto se pinta de verde
                            aplicarEstilo(tf, fila, col, "green", "white"); // Pone verde temporal
                        } else {
                            //Si es incorrecto se pinta rojo
                            aplicarEstilo(tf, fila, col, "red", "white"); // Pone rojo si hay error
                        }
                    } else {
                        // Si escribió una letra o borró la casilla, la dejamos vacía y en 0
                        tf.setText("");
                        modelo.setValor(fila, col, 0);
                        aplicarEstilo(tf, fila, col, "black", "white");
                    }
                });

                // Evento 2: Para quitar el color verde cuando pasamos a la siguiente casilla
                tf.focusedProperty().addListener((observable, viejo, nuevo) -> {
                    if (nuevo == false && modelo.esCasillaFija(fila, col) == false) {
                        // Si nos salimos de la casilla y el texto era verde, lo pasamos a azul
                        if (tf.getStyle().contains("green")) {
                            aplicarEstilo(tf, fila, col, "blue", "white");
                        }
                    }
                });
            }
        }
    }

    //Metodo que actualiza la pantalla
    private void cargarJuego(int numeroPlantilla) {
        modelo.cargarPlantilla(numeroPlantilla);
        TextField[][] casillas = vista.getCasillas();

        //Se recorre la matriz para dibujar los numeros en pantalla
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = modelo.getValor(i, j);
                TextField tf = casillas[i][j];

                if (modelo.esCasillaFija(i, j) == true) {
                    tf.setText(String.valueOf(valor));//Se pone el numero
                    tf.setEditable(false);//Se bloquea para que no se puedan borrar
                    aplicarEstilo(tf, i, j, "black", "#e0e0e0"); // Letra negra, fondo gris lo cual significa bloqueado
                } else {
                    tf.setText("");//Si no es fija, solo se queda blanco
                    tf.setEditable(true);//Se permite escribir
                    aplicarEstilo(tf, i, j, "black", "white"); // Letra negra, fondo blanco
                }
            }
        }
    }

    //
    private void aplicarEstilo(TextField tf, int fila, int col, String colorLetra, String colorFondo) {
        String bordeAbajo = "1";
        String bordeDerecha = "1";

        // Se hizo el borde más grueso para separar las cuadrículas de 3x3
        if (fila == 2 || fila == 5) bordeAbajo = "3";
        if (col == 2 || col == 5) bordeDerecha = "3";


        tf.setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1 " + bordeDerecha + " " + bordeAbajo + " 1; " +
                "-fx-text-fill: " + colorLetra + "; " +
                "-fx-background-color: " + colorFondo + "; " +
                "-fx-font-weight: bold;");
    }

    //Metodo para ventana emergente
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}