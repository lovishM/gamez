package gamez.bs.console;

import gamez.bs.enums.PositionTypes;
import gamez.bs.stucts.GameBoard;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class PaintGame {

    public static void paint(int[][] grid) {
        int i, j;
        System.out.print(" ");
        for (i = 0; i < GameBoard.MAX_X_SIZE; ++i) {
            System.out.print("___");
        }

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

        System.out.print(" ");
        for (i = 0; i < GameBoard.MAX_X_SIZE; ++i) {
            System.out.print("___");
        }
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

        paint(grid);
    }
}
