package Vista;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SudokuVista {

    private VBox panelPrincipal;//Esta es nuestra caja que acomodara todo
    private GridPane cuadricula;//Cuadricula para los numeros
    private TextField[][] casillas;//Cajitas de texto para los numeros

    //Botones del juego
    private Button btnPlantilla1;
    private Button btnPlantilla2;
    private Button btnPlantilla3;
    private Button btnValidar;

    public SudokuVista() {
        panelPrincipal = new VBox(20);//VBox con separacion de 20 pixel
        panelPrincipal.setAlignment(Pos.CENTER);//Esto es para centrar todo

        cuadricula = new GridPane();//Inicializador del tablero
        cuadricula.setAlignment(Pos.CENTER);
        casillas = new TextField[9][9];//Se crea la matriz de cajitas


        //Un for anidado para la cracion de las 81 TextFields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField tf = new TextField();
                tf.setPrefSize(40, 40);//Se hacen las cajitas
                tf.setAlignment(Pos.CENTER);//Se centra el numero
                tf.setFont(Font.font(16));//Tamaño de la letra

                casillas[i][j] = tf;//Se guarda en la matriz
                cuadricula.add(tf, j, i);//Se
            }
        }

        //Creacion de los botones con su texto
        btnPlantilla1 = new Button("Plantilla 1");
        btnPlantilla2 = new Button("Plantilla 2");
        btnPlantilla3 = new Button("Plantilla 3");

        //HBox es una caja horizontal para poner los 3 botones en línea
        HBox panelBotones = new HBox(10);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.getChildren().add(btnPlantilla1);
        panelBotones.getChildren().add(btnPlantilla2);
        panelBotones.getChildren().add(btnPlantilla3);

        btnValidar = new Button("Revisar Sudoku");
        //Metemos la cuadrícula y los botones adentro del Panel Principal grande
        panelPrincipal.getChildren().add(cuadricula);
        panelPrincipal.getChildren().add(panelBotones);
        panelPrincipal.getChildren().add(btnValidar);
    }

    public VBox getPanelPrincipal() {return panelPrincipal; }
    public TextField[][] getCasillas() { return casillas; }
    public Button getBtnPlantilla1() { return btnPlantilla1; }
    public Button getBtnPlantilla2() { return btnPlantilla2; }
    public Button getBtnPlantilla3() { return btnPlantilla3; }
    public Button getBtnValidar() { return btnValidar; }
}