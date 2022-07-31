package Obstacles;

/**Hordokat tartalmazo osztaly
 *
 */
public class Barrel  extends Obstacles{

    public static int[][] hordok = new int[100][4];

    public static int[][] getHordok() {
        return hordok;
    }

    public static void setHordok(int[][] hordok) {
        Barrel.hordok = hordok;
    }

    /**Hordo parameteres konstruktora
     *
     * @param db ennyi daranot csinál
     */
    public Barrel(int db){
        super(db, "H", hordok, 2, 4);
    }

    /**Hordo default konstruktora
     * alapból 1 darabot csinal
     */
    public Barrel(){
        super(1, "H", hordok, 2, 4);
    }
}