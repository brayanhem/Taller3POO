package Modelo;

public class Sudoku {
    private int[][] matriz;
    private boolean[][] casillasFijas;

    public Sudoku() {
        matriz = new int[9][9];
        casillasFijas = new boolean[9][9];
    }

    //Metodo para cargar una de las plantillas
    public void cargarPlantilla(int numeroPlantilla) {
        //Se limpian las matrices
        matriz = new int[9][9];
        casillasFijas = new boolean[9][9];

        //Se crea una matriz temporal
        int[][] plantilla = new int[9][9];

        //if-else para la eleccion de la plantilla que seleccione el usuario
        if (numeroPlantilla == 1) {
            int[][] p1 = {
                    {5,3,0, 0,7,0, 0,0,0}, {6,0,0, 1,9,5, 0,0,0}, {0,9,8, 0,0,0, 0,6,0},
                    {8,0,0, 0,6,0, 0,0,3}, {4,0,0, 8,0,3, 0,0,1}, {7,0,0, 0,2,0, 0,0,6},
                    {0,6,0, 0,0,0, 2,8,0}, {0,0,0, 4,1,9, 0,0,5}, {0,0,0, 0,8,0, 0,7,9}
            };
            plantilla = p1;
        } else if (numeroPlantilla == 2) {
            int[][] p2 = {
                    {0,0,0, 2,6,0, 7,0,1}, {6,8,0, 0,7,0, 0,9,0}, {1,9,0, 0,0,4, 5,0,0},
                    {8,2,0, 1,0,0, 0,4,0}, {0,0,4, 6,0,2, 9,0,0}, {0,5,0, 0,0,3, 0,2,8},
                    {0,0,9, 3,0,0, 0,7,4}, {0,4,0, 0,5,0, 0,3,6}, {7,0,3, 0,1,8, 0,0,0}
            };
            plantilla = p2;
        } else if (numeroPlantilla == 3) {
            int[][] p3 = {
                    {0,2,0, 6,0,8, 0,0,0}, {5,8,0, 0,0,9, 7,0,0}, {0,0,0, 0,4,0, 0,0,0},
                    {3,7,0, 0,0,0, 5,0,0}, {6,0,0, 0,0,0, 0,0,4}, {0,0,8, 0,0,0, 0,1,3},
                    {0,0,0, 0,2,0, 0,0,0}, {0,0,9, 8,0,0, 0,3,6}, {0,0,0, 3,0,6, 0,9,0}
            };
            plantilla = p3;
        }

        // Llenamos la matriz con el ciclo for anidado
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matriz[i][j] = plantilla[i][j];
                //Si el numero no es 0 significara que es un numero fijo de la plantilla
                if (plantilla[i][j] != 0) {
                    casillasFijas[i][j] = true;//Se marca como verdadero aqui para proteger la casilla
                }
            }
        }
    }

    //Metodo par guardar el valor que ingrese el usuario
    public void setValor(int fila, int col, int valor) {
        matriz[fila][col] = valor;
    }

    //Metodo para saber que numero hay guardado en una posicion
    public int getValor(int fila, int col) {
        return matriz[fila][col];
    }

    //Metodo para saber si una casilla es de las fijas
    public boolean esCasillaFija(int fila, int col) {
        return casillasFijas[fila][col];
    }

    //Aqui agrupe los 3 metodos de validacion del sudoku
    public boolean esValido(int fila, int col, int num) {
        if (validarFila(fila, col, num) == false) return false;
        if (validarColumna(fila, col, num) == false) return false;
        if (validarCuadricula(fila, col, num) == false) return false;

        return true;//SI pasa estas validaciones el numero es valido
    }

    //Regla 1 es que el numero no se repita en la misma fila
    public boolean validarFila(int fila, int col, int num) {
        for (int c = 0; c < 9; c++) {
            //se revisan las otras columnas de esa fila, si sale el numero es invalido
            if (c != col && matriz[fila][c] == num) {
                return false;
            }
        }
        return true;
    }
    //Regla 2 donde no se debe repetir en la misma columna
    public boolean validarColumna(int fila, int col, int num) {
        for (int f = 0; f < 9; f++) {
            //se revisan las otras filas de esa columna, si sale el numero entonces es invalido
            if (f != fila && matriz[f][col] == num) {
                return false;
            }
        }
        return true;
    }
    //Regla 3 que no este dentro de la misma cuadricula de 3x3
    public boolean validarCuadricula(int fila, int col, int num) {
        //Esto es para calcular la esquina superior izquierda del cuadro 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioCol = (col / 3) * 3;

        //Solo se recorren las 9 casillas/cuadritos de la subcuadricula
        for (int f = inicioFila; f < inicioFila + 3; f++) {
            for (int c = inicioCol; c < inicioCol + 3; c++) {
                if (f != fila || c != col) {
                    if (matriz[f][c] == num) {
                        return false;//El numero ya existe
                    }
                }
            }
        }
        return true;
    }

    //Meotodo para la revision
    public boolean estaResuelto() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = matriz[i][j];
                //Si se encuentra un 0 no ha terminado
                if (num == 0) {
                    return false; // Faltan números
                }
                //Si el numero esta rompiendo alguna de las erglas, esta mal
                if (validarFila(i, j, num) == false || validarColumna(i, j, num) == false || validarCuadricula(i, j, num) == false) {
                    return false; // Hay errores
                }
            }
        }
        return true; // Si los numeros estan correctas, gana
    }
}
