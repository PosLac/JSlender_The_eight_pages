package Obstacles;

/**Nagy fakat letrehozo osztaly
 *
 */
public class BigTrees extends Obstacles {
    public static int[][] nagyFak = new int[100][4];

    public static int[][] getNagyFak() {
        return nagyFak;
    }

    public static void setNagyFak(int[][] nagyFak) {
        BigTrees.nagyFak = nagyFak;
    }

    /**
     * Nagyfa default konstruktora
     * alapbol 5 darabot csinal
     */
    public BigTrees(){
        super(5, "F", nagyFak, 2, 2);
    }


    /**Nagyfa parameteres konstruktora
     *
     * @param db ennyi darabot csinal
     */
    public BigTrees(int db){
        super(db, "F", nagyFak, 2, 2);
    }
}