package Obstacles;

/**Sziklakat tartalmazo osztaly
 *
 */
public class Rock  extends Obstacles{

    public static int[][] sziklak= new int[100][4];

    public static int[][] getSziklak() {
        return sziklak;
    }

    public static void setSziklak(int[][] sziklak) {
        Rock.sziklak = sziklak;
    }

    /**Szikla parameteres konstruktora
     *
      * @param db ennyit csinal
     */
    public Rock(int db){
        super(db, "R", sziklak, 3, 3);
    }

    /**Szikla default konstruktora
     * alapbol 1-et csinal
     */
    public Rock(){
        super(1, "R", sziklak, 3, 3);
    }

}
