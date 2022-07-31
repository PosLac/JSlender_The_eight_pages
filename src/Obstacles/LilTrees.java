package Obstacles;


/**Kis fakat letrehozo osztaly
 *
 */
public class LilTrees extends Obstacles {

    public static int[][] kisFak = new int[100][4];

    /**
     * Kisfak parameteres konstruktora
     *
     * @param db ennyit csinal
     */
    public LilTrees(int db){
        super(db, "f", kisFak, 1, 1);
    }


    /**
     * Kisfak default konstruktora
     * alapbol 5-ot csinal
     */
    public LilTrees(){
        super(5, "f", kisFak, 1, 1);
    }

    public static int[][] getKisFak() {
        return kisFak;
    }

    public static void setKisFak(int[][] kisFak) {
        LilTrees.kisFak = kisFak;
    }
}