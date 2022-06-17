package com.sudoku;

public class Sudoku implements SufokuTDA {

    private int[][] sol = inicializarSudoku();
    private boolean[][] inicial = generarVectorBoolean(sol);


    @Override
    public int[][] inicializarSudoku() {
        int[][] sudokuInicial = {
                {0, 0, 2, 9, 0, 0, 5, 8, 0},
                {3, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 5, 0, 0, 8, 2, 0, 6, 0},
                {0, 3, 1, 0, 0, 0, 9, 0, 0},
                {0, 0, 9, 0, 0, 0, 7, 0, 0},
                {0, 0, 4, 0, 0, 0, 6, 2, 0},
                {0, 9, 0, 2, 6, 0, 0, 7, 0},
                {0, 0, 0, 0, 0, 8, 0, 0, 9},
                {0, 7, 5, 0, 0, 1, 2, 0, 0}
        };
        return sudokuInicial;
    }

    private boolean[][] generarVectorBoolean(int[][] sol) {
        boolean[][] inicial = new boolean[9][9];
        for (int fila = 0; fila < inicial.length; fila++) {
            for (int columna = 0; columna < inicial.length; columna++) {
                //definir cual va a ser el criterio para false
                inicial[fila][columna] = sol[fila][columna] != 0;
            }
        }
        return inicial;
    }

    @Override
    public void agregar(int[][] sol, int i, int j, int k) {
        sol[i][j] = k;
    }

    @Override
    public void sacar(int numero, int[][] tablero, int fila, int columna) {

    }

    @Override
    public int[][] resolver(int i, int j, int[][] sol) {
        //TODO
        //seria k
        //si la celda esta vacia
        if (!this.inicial[i][j]) {
            for (int numero = 1; numero <= 9; numero++) {
                agregar(sol, i, j, numero);//sol[i][j] = k;

                //si cumple con las reestricciones

                if (sePuede(i, j, sol, numero)) {
                    if (i == 8 && j == 8) {
                        //no entra xq no llega a la solucion
                        mostrar(sol);
                        return sol;
                    }
                    if (i < 8 && j == 8) {
                        return resolver(i + 1, 0, sol);
                    }
                    if (i <= 8 && j < 8) {
                        return resolver(i, j + 1, sol);
                    }
                } else {
                    //revisar este caso cuando llega a i<9 && j==8 y no se puede, corta.
                    //TODO
                    sol[i][j] = 0;

                }
            }
        }
        /*la celda tiene un numero
         * se comprueba si se llego a la solucion o se vuelve a llamar a la funcion hasta llegar a la sol*/
        else {
            if (i == 8 && j == 8) {
                mostrar(sol);
                return sol;
            }
            if (i < 8 && j == 8) {
                return resolver(i + 1, 0, sol);
            }
            if (i <= 8 && j < 8) {
                return resolver(i, j + 1, sol);
            }
        }
        return sol;
    }

    private boolean sePuede(int filaActual, int columnaActual, int[][] sol, int numero) {
        boolean valido = true;
        //k
        int filaEvaluada = 0;
        //l
        int columnaEvaluada = 0;

        //comprueba por fila
        while (filaEvaluada <= 8 && valido) {
            //se mueve en la misma columna, distinta fila

            if (sol[filaActual][columnaActual] == sol[filaEvaluada][columnaActual] && filaEvaluada != filaActual) {
                return false;
            }
            //sale del while cuando termina de evaluar todas las filas
            filaEvaluada += 1;

        }
        while (columnaEvaluada <= 8 && valido) {
            if (sol[filaActual][columnaActual] == sol[filaActual][columnaEvaluada]
                    && columnaEvaluada != columnaActual) {
                return false;
            }
            columnaEvaluada += 1;
        }

        int f = corresponde3x3(filaActual);
        int c = corresponde3x3(columnaActual);

        while (f < corresponde3x3(filaActual) + 3 && valido) {
            while (c < corresponde3x3(columnaActual) + 3 && valido) {
                if (sol[filaActual][columnaActual] == sol[f][c] && f != filaActual && c != columnaActual) {
                    return false;
                }
                c += 1;

            }
            f += 1;
            c = corresponde3x3(columnaActual);
        }
        return true;
    }

    private int corresponde3x3(int posicion) {
        int k;
        int resultado = 0;

        if (posicion % 3 == 0) {
            k = posicion / 3;
        } else {
            k = (posicion / 3) + 1;
        }

        switch (k) {
            case 1:
                resultado = 0;
                break;
            case 2:
                resultado = 3;
                break;
            case 3:
                resultado = 6;
                break;
        }
        return resultado;
    }


    @Override
    public void mostrar(int[][] sol) {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (this.inicial[fila][columna]) {
                    System.out.print("[" + sol[fila][columna] + "]" + " ");
                } else {
                    System.out.print(sol[fila][columna] + "   ");
                }

                if (columna == 2 || columna == 5 || columna == 8) {
                    System.out.print("  |  ");
                }

            }
            System.out.println();
            if (fila == 2 || fila == 5 || fila == 8) {
                System.out.println("-----------------------------------------");
            }

        }
    }

    @Override
    public void revertir(int[][] sudoku, int numero, int[][] tablero, int fila, int columna) {

    }

    @Override
    public void limpiar(int[][] sudoku) {

    }
}
