package Obstacles;

/**Teherautokat tartalmazo osztaly
 *
 */
public class BigCar  extends Obstacles{
    public static int[][] teherAutok= new int[100][4];

    /**Teherauto parameteres konstruktora
     *
     * @param db ennyi darabot csinal
     */
    public BigCar(int db){
        super(db, "T", teherAutok, 3, 5);
    }

    /**Teherauto default konstruktora
     * alapbol 1-et csinal
     */
    public BigCar(){
        super(1, "T", teherAutok, 3, 5);
    }

    public static int[][] getTeherAutok() {
        return teherAutok;
    }

    public static void setTeherAutok(int[][] teherAutok) {
        BigCar.teherAutok = teherAutok;
    }
}
