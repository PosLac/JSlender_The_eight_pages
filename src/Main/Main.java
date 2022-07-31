package Main;

import Entities.Papers;
import Entities.Player;
import Entities.Slender;
import Level.MakeLevel;
import Obstacles.House;
import Obstacles.Obstacles;


/**Magat a lepeseket vegzo, valamint a metodusokat meghivo osztaly
 *
 */
public class Main {
    private static final int height = 15;
    private static final int width = 15;
    private static boolean gotcha = false;
    private static boolean end = false;
    private static String[][] level = new String[height + 2][width + 2];       //17 17
    private static int kozel = 0;
    static final int delay = 1000;

    public static Player player = new Player();
    public static Slender slender = new Slender();

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public boolean isEnd() {
        return end;
    }

    public static void setEnd(boolean end) {
        Main.end = end;
    }

    public static String[][] getLevel() {
        return level;
    }

    public void setLevel(String[][] level) {
        Main.level = level;
    }

    public static int getDelay() {
        return delay;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }

    public static Slender getSlender() {
        return slender;
    }

    public static void setSlender(Slender slender) {
        Main.slender = slender;
    }

    public static void main(String[] args) throws InterruptedException {
        MakeLevel.initlevel();
        House.makeHouse();
        Obstacles.makeObstacles();
        welcome();       //ez egy kicsit sokáig tart, ha csak próbálgatod, nyugodtan töröld ki

        Papers papers = new Papers();
        int iteration = 1;

        while(!end) {

            player.playerMove();

            if(!player.isHiba()) {

                nearThrice();
                papers.pickPaper();
                if(!end) {
                    slender.moveSlender(iteration, player.getPlayer());
                }
                draw(papers.getMark(), papers.getPapers(), papers.getOnMap());

                for (int i = 0; i < 8; i++) {

                    if (calculateDistance(player.getPlayer()[0], player.getPlayer()[1], papers.getPapers()[i][0], papers.getPapers()[i][1]) < 2) {
                        papers.setPaperOnMap(i, false);
                    }
                }
                System.out.println("Felszedett papírok: " + Papers.getPaperCount());
                System.out.println("Lépések: " + iteration);
                iteration++;
            }

            if(end){
                System.out.println("Gratulálok, belesétáltál Slenderman karjaiba");
            }

            else if (gotcha) {
                end = true;
                System.out.println("Gratulálok, meghaltál");
                break;
            }

            if (Papers.getPaperCount() == 8) {
                end = true;
                System.out.println("Király vagy, megvan a 8 papír, nyertél!");
                break;
            }
        }
    }

    /**Meno udvozlet
     *
     * @throws InterruptedException muszaj a Thread.sleep miatt
     */
    private static void welcome() throws InterruptedException {
        System.out.println("\nA feladatodat gondolom már tudod, 8 papír van elhelyezve a pálya tereptárgyain, ezeket kell összeszedned, de vigyázz!");
        System.out.println("Slenderman a nyomodban van, ha sikerül összeszedned a 8 papírt, nyertél");
        System.out.println("De ha nem, véged (x_x)");
        Thread.sleep(10000);
        System.out.println("\nAz a/d/w/s billentyűk lenyomásával tudsz mozogni");
        coolLoadingAnimation();
        System.out.println("         Minden lépésnél nyomj egy Entert");
        coolLoadingAnimation();
        System.out.println("                Jó szórakozást :)");
        Thread.sleep(delay);
    }

    /**Meno animacioszeru cucc
     *
     * @throws InterruptedException kell a Thread.sleep miatt
     */
    private static void coolLoadingAnimation() throws InterruptedException {
        Thread.sleep(delay);
        System.out.print("                    .");
        Thread.sleep(delay);

        for (int i = 0; i < 4; i++) {
            System.out.print(".");
            Thread.sleep(delay);
        }
        System.out.println();
    }

    /**Kiszamitja ket pont tavolsagat
     *
     * @param row1  elso objektum sora
     * @param col1  elso objektum oszlopa
     * @param row2  madoik objektum sora
     * @param col2  madoik objektum oszlopa
     * @return  ket pont tavolsaga
     */
    public static int calculateDistance(int row1, int col1, int row2, int col2) {
        int rowDifference = Math.abs(row1 - row2);
        int colDifference = Math.abs(col1 - col2);
        return rowDifference + colDifference;
    }

    /**Kirajzolja a konzolra a megadott ertekeket
     *
     * @param paperMark     papir jele a palyan
     * @param paperOnMap    igaz, ha az adott papir a palyan van
     * @param paperTomb     papirokat tartalmazo tomb
     */
    private static void draw(String paperMark, int[][] paperTomb, boolean[] paperOnMap) {

        String[][] board = level;
        int playerX = player.getPlayer()[0];
        int playerY = player.getPlayer()[1];
        int slenderX = slender.getSlender()[0];
        int slenderY = slender.getSlender()[1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (slender.getSlenderOnMap() && i == slenderX && j == slenderY && i == playerX && j == playerY){
                    System.out.print(slender.getMark());
                    gotcha = true;
                }
                else if (i == playerX && j == playerY) {
                    System.out.print(player.getMark());
                }
                else if (slender.getSlenderOnMap() && i == slenderX && j == slenderY && calculateDistance(playerX, playerY, slenderX, slenderY) <= 3){
                    System.out.print(slender.getMark());
                }
                else if (Papers.isPaper(paperTomb, paperOnMap, i, j) && Papers.seePaper(paperOnMap, i, j, playerX, playerY)) {       //&&
                    System.out.print(paperMark);
                }
                else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**Megnezi, hogy egymas utan 3x egymas mellett volt-e a Slender es a jatekos
     *
     */
    private static void nearThrice(){

        if(calculateDistance(player.getPlayer()[0], player.getPlayer()[1], slender.getSlender()[0], slender.getSlender()[1]) < 2){
            kozel++;
        }
        else{
            kozel--;
        }

        //egymás után 3x mellette van
        if(kozel >= 3){

            if(slender.getPaperCountForSlender() == 1 && slender.getRandomForSlender() == 0){
                gotcha = true;
            }
            else if(slender.getPaperCountForSlender() == 2 && slender.getRandomForSlender() == 0){
                gotcha = true;
            }
            else if(slender.getPaperCountForSlender() == 3 && (slender.getRandomForSlender() == 0 || slender.getRandomForSlender() == 1)){
                gotcha = true;
            }
        }
    }

    public static int getKozel() {
        return kozel;
    }

    public static void setKozel(int kozel) {
        Main.kozel = kozel;
    }

    public static boolean isGotcha() {
        return gotcha;
    }

    public static void setGotcha(boolean gotcha) {
        Main.gotcha = gotcha;
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           //go for 66% ;)