package Obstacles;

/**Autot tartalmazo osztaly
 *
 */
public class LilCar extends Obstacles{

    public static int[][] autok= new int[100][4];

    /**Auto parameteres konstruktora
     *
     * @param db ennyi darabot csinal
     */
    public LilCar(int db){
        super(db, "A", autok, 2, 3);
    }

    /**Auto default konstruktora
     * alapbol 1-et csinal
     */
    public LilCar(){
        super(1, "A", autok, 2, 3);
    }

    public static int[][] getAutok() {
        return autok;
    }

    public static void setAutok(int[][] autok) {
        LilCar.autok = autok;
    }
}
