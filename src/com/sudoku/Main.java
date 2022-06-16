package com.sudoku;

public class Main {

    public static void main(String[] args) {
	Sudoku sudoku = new Sudoku();
	int[][] sol =  sudoku.inicializarSudoku();
    //sudoku.mostrar(inicial);
	int i= 0;
	int j = 0;
	sol = sudoku.resolver(i,j,sol);
	sudoku.mostrar(sol);



    }

}
