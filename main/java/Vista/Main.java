package Vista;

import Controlador.SudokuControlador;
import Modelo.Sudoku;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage ventana) {
        Sudoku modelo = new Sudoku();
        SudokuVista vista = new SudokuVista();
        SudokuControlador controlador = new SudokuControlador(modelo, vista);
        //Se coloca nuestro panel principal en una escena
        Scene escena = new Scene(vista.getPanelPrincipal(), 450, 500);

        //Configuracion de la ventana
        ventana.setTitle("Sudoku");//Titutlo
        ventana.setScene(escena);//Se pega la escena de la ventana
        ventana.show();//Se despliega
    }

    public static void main(String[] args) {
        launch(args);
    }
}
