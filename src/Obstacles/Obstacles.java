package Obstacles;

import Main.Main;

import java.util.Random;

/**Kulonbozo tereptargyakat letrehozo osztaly
 *
 */
public class Obstacles {
    static final Random random = new Random();
    static final String[][] level = Main.getLevel();

    public int db;
    public String mark;
    public int[][] obstacles = new int[100][4];
    public int szel;
    public int hossz;

    /**Tereptárgyak parameteres konstruktora
     *
     * @param db    ennyi darab lesz az adott targybol
     * @param mark  az adott targy jele a palyan
     * @param obstacles adott targy tombje
     * @param szel  targy szelessege
     * @param hossz targy hosszusaga
     */
    public Obstacles(int db, String mark, int[][] obstacles, int szel, int hossz) {
        this.db = db;
        this.mark = mark;
        this.obstacles = obstacles;
        this.szel = szel;
        this.hossz = hossz;

        int[][] test = new int[db][3];
        int angle;

        int bad;
        int minus = 1;
        int plus = 2;

        //kisfák és nagyfák lehetnek a kerítés mentén, de más tereptárgy nem
        if (mark.equals("f")) {
            minus = -1;
            plus = 1;
        }
        else if (mark.equals("F")) {
            minus = 0;
        }

        for (int i = 0; i < db; i++) {
            angle = random.nextInt(2);  //vízszintes vagy függőleges (0|1)
            int tmp;

            if (angle == 0) {   //ha igaz, vízszintes, egyébként függőleges
                tmp = szel;
                szel = hossz;
                hossz = tmp;
            }

            do {
                bad = 0;
                obstacles[i][0] = random.nextInt(Main.getHeight() - (hossz + minus)) + plus;
                obstacles[i][1] = random.nextInt(Main.getWidth() - (szel + minus)) + plus;

                for (int j = 0; j < hossz && bad == 0; j++) {
                    for (int k = 0; k < szel; k++) {
                        if (level[obstacles[i][0] + j][obstacles[i][1] + k].equals(" ") &&
                                !level[obstacles[i][0] + j][obstacles[i][1] + k + 1].equals(".") &&
                                !level[obstacles[i][0] + j][obstacles[i][1] + k - 1].equals(".")) {
                            test[i][0] = obstacles[i][0];
                            test[i][1] = obstacles[i][1];
                        } else {
                            bad++;
                        }
                    }
                }
            } while (bad != 0);

            for (int j = 0; j < hossz; j++) {
                for (int k = 0; k < szel; k++) {
                    level[test[i][0] + j][test[i][1] + k] = mark;
                }
            }
        }
    }

    public static Random getRandom() {
        return random;
    }

    public static String[][] getLevel() {
        return level;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int[][] getObstacles() {
        return obstacles;
    }

    public void setObstacles(int[][] obstacles) {
        this.obstacles = obstacles;
    }

    public int getSzel() {
        return szel;
    }

    public void setSzel(int szel) {
        this.szel = szel;
    }

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    /**Default konstruktor
     *
     */
    public Obstacles(){

    }

    /**Ez a metodus van meghivva a Main-ben, ahol letrehozza a megadott mennyisegu tereptargyakat
     *
     */
    public static void makeObstacles(){
        Barrel barrels = new Barrel(1);
        BigCar teherautok = new BigCar(1);
        LilCar autok = new LilCar(1);
        Rock sziklak = new Rock(1);
        BigTrees nagyFak = new BigTrees(7);
        LilTrees kisFak = new LilTrees(7);
    }
}
