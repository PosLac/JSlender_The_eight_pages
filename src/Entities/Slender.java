package Entities;

import Main.Main;


/**Slenderman kezdokoordinatainak generalasat es teleportalasat vegzo osztaly
 *
 */
public class Slender extends Entity{

    private int randomForSlender;
    private int paperCountForSlender;
    private int[][] slender = new int[1][3];
    private boolean[] slenderOnMap = new boolean[1];

    public Slender() {
        this.setOnMap(slenderOnMap);
        this.setMark("X");
        this.setEntities(slender);
        slender[0][0] = 0;
        slender[0][1] = 0;
    }

    public int getRandomForSlender() {
        return randomForSlender;
    }

    public void setRandomForSlender(int randomForSlender) {
        this.randomForSlender = randomForSlender;
    }

    public int getPaperCountForSlender() {
        return paperCountForSlender;
    }

    public void setPaperCountForSlender(int paperCountForSlender) {
        this.paperCountForSlender = paperCountForSlender;
    }

    public int[] getSlender() {
        return slender[0];
    }

    public void setSlender(int[] slender) {
        this.slender[0] = slender;
    }

    public boolean getSlenderOnMap() {
        return slenderOnMap[0];
    }

    public void setSlenderOnMap(boolean slenderOnMap) {
        this.slenderOnMap[0] = slenderOnMap;
    }

    /**Kezdokoordinatakat general a Slendermannek
     *
     * @param level maga a palya
     * @return  egy random sor es egy random oszlop
     */
    public int[] startingCoordinatesForSlender(String[][] level) {
        int randomRow;
        int randomCol;
        do {
            randomRow = getRandom().nextInt(Main.getHeight());
            randomCol = getRandom().nextInt(Main.getWidth());
        } while (!level[randomRow][randomCol].equals(" "));
        return new int[]{randomRow, randomCol};
    }

    /**Teleportalja a Slendert az alapjan, hogy mennyi papirja van a jatekosnak
     *
     * @param playerRow jatekos sora
     * @param playerCol jatekos oszlopa
     * @param paperCount    ennyi papirja van a jatekosnak
     * @return  egy random sort es egy random oszlopot,ide teleportal a Slender
     */
    public int[] teleportSlender(int playerRow, int playerCol, int paperCount) {

        int randomRow;
        int randomCol;

        if(paperCount < 2){
            //kettőnél kevesebb papír
            setPaperCountForSlender(0);
            do {
                randomRow = getRandom().nextInt(Main.getHeight()-1) + 1;
                randomCol = getRandom().nextInt(Main.getWidth()-1) + 1;
            } while (calculateDistance(randomRow, randomCol, playerRow, playerCol) < 5);

            return new int[]{randomRow, randomCol};
        }

        else if(paperCount < 4 && paperCount >= 2){
            //kettőnél több, de 4-nél kevesebb papír
            paperCountForSlender = 1;
            //33% (0 -> gotcha)
            randomForSlender = getRandom().nextInt(3);

            do {
                randomRow = getRandom().nextInt(Main.getHeight()-1) + 1;
                randomCol = getRandom().nextInt(Main.getWidth()-1) + 1;
            } while (calculateDistance(randomRow, randomCol, playerRow, playerCol) > 5);

            return new int[]{randomRow, randomCol};
        }
        else if(paperCount < 6 && paperCount >= 4){
            //4-nél több, de 6-nál kevesebb papír
            paperCountForSlender = 2;
            //50% (0 -> gotcha)
            randomForSlender = getRandom().nextInt(2);

            do {
                randomRow = getRandom().nextInt(Main.getHeight()-1) + 1;
                randomCol = getRandom().nextInt(Main.getWidth()-1) + 1;
            } while (calculateDistance(randomRow, randomCol, playerRow, playerCol) > 4);

            return new int[]{randomRow, randomCol};
        }
        else{
            //6-nál több papír
            paperCountForSlender = 3;
            //66% (0, vagy 1 -> gotcha)
            randomForSlender = getRandom().nextInt(3);

            do {
                randomRow = getRandom().nextInt(Main.getHeight()-1) + 1;
                randomCol = getRandom().nextInt(Main.getWidth()-1) + 1;
            } while (calculateDistance(randomRow, randomCol, playerRow, playerCol) > 3);

            return new int[]{randomRow, randomCol};
        }
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

    /**Minden 5. korben random lepteti a Slendert, kulonben az osszegyujtott papirok alapjan
     *
     * @param iteration ennyi kor ment le eddig
     * @param playerCoordinates a jatekos koordinatai
     */
    public void moveSlender(int iteration, int[] playerCoordinates){
        //slender léptetése
        if (getOnMap()[0]) {
            if(iteration % 5 == 0){
                setSlender(startingCoordinatesForSlender(Main.getLevel()));
            }
            else {
                setSlender(teleportSlender(playerCoordinates[0], playerCoordinates[1], Papers.getPaperCount()));
            }
        }
    }
}
