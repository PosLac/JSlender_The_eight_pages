package Entities;

import Main.Main;

/**Papirokat letrehozo osztaly, ami megallapitja, hogy a jatekos latja-e az adott papirt,
 * valamint, hogy az adott papir meg a palyan van-e
 */
public class Papers extends Entity{

    private static int paperCount = 0;
    private int[][] paperTomb = new int[10][3];

    public int[][] getPaperTomb() {
        return paperTomb;
    }

    public void setPaperTomb(int[][] paperTomb) {
        this.paperTomb = paperTomb;
    }

    public static int getPaperCount() {
        return paperCount;
    }

    public static void setPaperCount(int paperCount) {
        Papers.paperCount = paperCount;
    }

    public int[][] getPapers() {
        return paperTomb;
    }

    public void setPapers(int i, int[] coordinates) {
        this.paperTomb[i] = coordinates;
    }

    public boolean[] getPaperOnMap() {
        return this.getOnMap();
    }

    public void setPaperOnMap(int i, boolean bool) {
        this.getOnMap()[i] = bool;
    }

    public Papers() {

        this.setMark("■");

        for (int i = 0; i < 8; i++) {

            this.setPapers(i, getrandomCoordinatesForPapers(Main.getLevel()));

            for (int j = 0; j < i; j++) {

                if (this.getPapers()[i][0] == this.getPapers()[j][0] && this.getPapers()[i][1] == this.getPapers()[j][1]) {
                    i--;
                    break;
                }
            }
            this.setPaperOnMap(i, true);
        }
    }

    /**Megnezi, hogy a jatekos lathatja-e az adott papirt, azaz kozvetlenul melelttuk van-e
     *
     * @param paperOnMap    igaz, ha az adott papir a palyan van
     * @param i palya sora
     * @param j palya oszlopa
     * @param playerX   jatekos sora
     * @param playerY   jatekos oszlopa
     * @return  Igaz, ha a jatekos latja a papirt, hamis, ha nem
     */
    public static boolean seePaper(boolean[] paperOnMap, int i, int j, int playerX, int playerY) {

        for (int p = 0; p < 8; p++) {
            if (paperOnMap[p] && calculateDistance(playerX, playerY, i, j) < 2) {
                return true;
            }
        }
        return false;
    }

    /**Megnezi, hogy a palya adott pontjan van-e papir
     *
     * @param papers    papirok koordinatait tartalmazo tomb
     * @param paperOnMap    igaz, ha az adott papir a palyan van
     * @param i palya sora
     * @param j palya oszlopa
     * @return  Igaz, ha a palya adott pontjan van papir
     */
    public static boolean isPaper(int[][] papers, boolean[] paperOnMap, int i, int j) {

        for (int p = 0; p < 8; p++) {
            if (paperOnMap[p] && i == papers[p][0] && j == papers[p][1]) {
                return true;
            }
        }
        return false;
    }

    /**Meghivja a calculateDistance metodust a Main osztalybol
     *
     * @param row1  elso objektum sora
     * @param col1  elso objektum oszlopa
     * @param row2  madoik objektum sora
     * @param col2  madoik objektum oszlopa
     * @return  ket pont tavolsaga
     */
    public static int calculateDistance(int row1, int col1, int row2, int col2) {

        return Main.calculateDistance(row1, col1, row2, col2);
    }

    /**Random koordinatakat general a papirpknak
     *
     * @param level maga a palya
     * @return  ket random koordinata
     */
    public static int[] getrandomCoordinatesForPapers(String[][] level) {

        int randomRow= getRandom().nextInt(Main.getHeight());
        int randomCol= getRandom().nextInt(Main.getWidth());

        for (int i = 0; i < 9; i++) {
            do {
                randomRow = getRandom().nextInt(Main.getHeight()) + 1;
                randomCol = getRandom().nextInt(Main.getWidth()) + 1;
            } while (level[randomRow][randomCol].equals(" ")  ||  level[randomRow][randomCol].equals("O") ||
                    level[randomRow][randomCol].equals("X") || level[randomRow][randomCol].equals(".") ||
                    level[randomRow][randomCol].equals("f"));
        }
        return new int[]{randomRow, randomCol};
    }

    /**Felszedi az adott papirt
     *
     */
    public void pickPaper(){
        //papír felszedése
        for (int i = 0; i < 8; i++) {

            if ((calculateDistance(Main.player.getPlayer()[0], Main.player.getPlayer()[1], getPapers()[i][0], getPapers()[i][1]) < 2) && getPaperOnMap()[i]) {
                paperCount++;
                Main.slender.setSlenderOnMap(true);
            }
        }
    }
}