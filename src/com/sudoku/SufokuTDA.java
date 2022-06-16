package com.sudoku;

public interface SufokuTDA {
    int[][] inicializarSudoku();
    void agregar(int[][] sol, int i, int j,int numero);
    void sacar(int numero, int[][] tablero, int fila, int columna);
    int[][] resolver(int i, int j,int[][] sol);
    void mostrar(int[][] sudoku);
    void revertir(int[][] sudoku, int numero, int[][] tablero, int fila, int columna);
    void limpiar(int[][] sudoku);



}
