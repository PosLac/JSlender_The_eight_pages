package Level;

import Main.Main;

/**Palya inicializalasat vegzo osztaly
 *
 */
public class MakeLevel {

    /**Inicializalja a palyat, "keritest" rak a szeleire, amin kivul nem lehet menni
     *
     */
    public static void initlevel() {
        String[][] level = Main.getLevel();

        for (int row = 0; row < level.length; row++) {

            for (int col = 0; col < level[0].length; col++) {
                level[row][col] = " ";


                if (row == 0 && col == 0) {
                    level[row][col] = "╔";
                }
                else if (row == 0 && col == level[0].length - 1) {
                    level[row][col] = "╗";
                }
                else if (row == level.length - 1 && col == 0) {
                    level[row][col] = "╚";
                }
                else if (row == level.length - 1 && col == level[0].length - 1) {
                    level[row][col] = "╝";
                }
                else if (row == level.length - 1 || row == 0) {
                    level[row][col] = "═";
                }
                else if (col == 0 || col == level[0].length - 1) {
                    level[row][col] = "║";
                }
            }
        }
    }
}
