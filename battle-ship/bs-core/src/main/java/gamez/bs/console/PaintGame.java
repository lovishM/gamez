package gamez.bs.console;

import gamez.bs.enums.PositionTypes;
import gamez.bs.stucts.GameBoard;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class PaintGame {

    /**
     * Paints a single grid
     *
     * @param grid      grid to be painted on the console
     */
    public static void paintOne(int[][] grid) {
        int i, j;

        printPaddding('_');

        System.out.println();
        for (i = 0; i < GameBoard.MAX_X_SIZE; ++i) {
            for (j = 0; j < GameBoard.MAX_Y_SIZE; ++j) {

                if (j == 0) System.out.print("|");

                if (grid[i][j] == PositionTypes.EMPTY.type()) System.out.print(" . ");
                else if (grid[i][j] == PositionTypes.PLAYED.type()) System.out.print(" * ");
                else if (grid[i][j] == PositionTypes.SUNK.type()) System.out.print("-#-");
                else System.out.print("-" + ((char) grid[i][j]) + "-");

                if (j == (GameBoard.MAX_Y_SIZE -1)) System.out.print("|");
            }
            System.out.println();
        }

        printPaddding('_');
    }

    /**
     * Paints multiple grids
     *
     * @param aGrid     first grid to be painted on the console
     * @param bGrid     second grid to be painted on the console
     */
    public static void paintAll(int[][] aGrid, int[][] bGrid) {
        int i, j;

        printPaddding('_');
        printPaddding(' ');
        printPaddding('_');

        System.out.println();
        for (i = 0; i < GameBoard.MAX_X_SIZE; ++i) {
            for (j = 0; j < GameBoard.MAX_Y_SIZE; ++j) {

                if (j == 0) System.out.print("|");

                if (aGrid[i][j] == PositionTypes.EMPTY.type()) System.out.print(" . ");
                else if (aGrid[i][j] == PositionTypes.PLAYED.type()) System.out.print(" * ");
                else if (aGrid[i][j] == PositionTypes.SUNK.type()) System.out.print("XXX");
                else System.out.print("-" + ((char) aGrid[i][j]) + "-");

                if (j == (GameBoard.MAX_Y_SIZE -1)) System.out.print("|");
            }

            printPaddding(' ');

            for (j = 0; j < GameBoard.MAX_Y_SIZE; ++j) {

                if (j == 0) System.out.print("|");

                if (bGrid[i][j] == PositionTypes.EMPTY.type()) System.out.print(" . ");
                else if (bGrid[i][j] == PositionTypes.PLAYED.type()) System.out.print(" * ");
                else if (bGrid[i][j] == PositionTypes.SUNK.type()) System.out.print("XXX");
                else System.out.print("-" + ((char) bGrid[i][j]) + "-");

                if (j == (GameBoard.MAX_Y_SIZE -1)) System.out.print("|");
            }
            System.out.println();
        }

        printPaddding('_');
        printPaddding(' ');
        printPaddding('_');
    }

    private static void printPaddding(char ch) {

        String str = ch + "" + ch + ch;

        System.out.print(" ");
        for (int i = 0; i < GameBoard.MAX_X_SIZE; ++i) { System.out.print(str); }
        System.out.print(" ");
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 0, 1, 1, 1, 1, 0, 0},
                {'d', 'd', 0, 0, 0, 'C', 'C', 'C', 'C', -1},
                {0, 1, 1, 0, 1, 0, 1, 0, 0, 1},
                {1, 's', 's', 's', 0, 1, -1, -1, 'b', 'b'},
                {1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 'c', 'c', 'c', 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 1, 0, 0},
        };

        paintAll(grid, grid);
    }
}
