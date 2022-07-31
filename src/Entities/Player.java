package Entities;

import Main.Main;

import java.util.Scanner;


/**A jatekost input alapjani lepteteset vegzo osztaly
 */
public class Player extends Entity{

    private boolean hiba = false;
    private int[][] player = new int[1][3];
    private final boolean[] playerOnMap = new boolean[2];

    public Player() {
        this.setOnMap(playerOnMap);
        this.setMark("O");
        this.setEntities(player);
        player[0][0] = 1;
        player[0][1] = 1;
    }

    public void setPlayer(int[][] player) {
        this.player = player;
    }

    public boolean[] getPlayerOnMap() {
        return playerOnMap;
    }

    public boolean isHiba() {
        return hiba;
    }

    public void setHiba(boolean hiba) {
        this.hiba = hiba;
    }

    public int[] getPlayer() {
        return player[0];
    }

    public void setPlayer(int row, int col) {
        this.player[0][0] = row;
        this.player[0][1] = col;
    }

    /**Mozgatja a jatekost a bevitt input alapjan
     */
    public void playerMove(){
        int row = this.getPlayer()[0];
        int col = this.getPlayer()[1];
        String[][] level = Main.getLevel();

        Scanner input = new Scanner(System.in);

        setHiba(false);
        switch (input.next()) {
            case "w":
                if (level[row - 1][col].equals(" ") || level[row - 1][col].equals("f")
                        || level[row - 1][col].equals("F") || level[row - 1][col].equals(".")) {
                    row--;
                }
                break;
            case "s":
                if (level[row + 1][col].equals(" ") || level[row + 1][col].equals("f")
                        || level[row + 1][col].equals("F") || level[row + 1][col].equals(".")) {
                    row++;
                }
                break;
            case "d":
                if (level[row][col + 1].equals(" ") || level[row][col + 1].equals("f")
                        || level[row][col + 1].equals("F") || level[row][col + 1].equals(".")) {
                    col++;
                }
                break;
            case "a":
                if (level[row][col - 1].equals(" ") || level[row][col - 1].equals("f") ||
                        level[row][col - 1].equals("F") || level[row][col - 1].equals(".")) {
                    col--;
                }
                break;
            default:
                System.err.println("A négy irány egyikét add meg (a/d/w/s)");
                hiba = true;
                break;
        }
        if(row == Main.slender.getSlender()[0] && col == Main.slender.getSlender()[1]){
            Main.setEnd(true);
        }

        this.setPlayer(row, col);
    }
}
