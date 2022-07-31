package Obstacles;

import Entities.Entity;
import Main.Main;

/**Hazat letrehozo osztaly
 *
 */
public class House{

    /**Letrehoz egy hazat ket bejarattal
     *
     */
    public static void makeHouse() {
        String[][] level = Main.getLevel();

        int bad;

        int row;        //6
        int col;        //7

        do {
            bad = 0;
            row = Entity.getRandom().nextInt(Main.getHeight() - 8) + 2;
            col = Entity.getRandom().nextInt(Main.getWidth()- 7) + 2;

            for (int j = 0; j < 7 && bad == 0; j++) {
                for (int k = 0; k < 6; k++) {
                    if (!level[row + j][col + k].equals(" ")) {
                        bad++;
                    }
                }
            }
        } while (bad != 0);

        for (int i = row; i < row+7; i++) {
            for (int j = col; j < col+6; j++) {

                if (i == row && j == col) {
                    level[i][j] = "╔";
                }
                else if (i == row && j == col+5) {
                    level[i][j] = "╗";
                }
                else if (i == row+6 && j == col) {
                    level[i][j] = "╚";
                }
                else if (i == row+6 && j == col+5) {
                    level[i][j] = "╝";
                }
                else if (i == row || i == row+6) {
                    level[i][j] = "═";
                }
                else if (j == col || j == col+5) {
                    level[i][j] = "║";
                }
            }
        }
        level[row+2][col] = ".";
        level[row+4][col+5] = ".";
    }
}
