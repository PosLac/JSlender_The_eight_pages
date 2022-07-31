package Entities;

import java.util.Random;

/**A jatekost, Slendert es a papirokat egybefoglalo ososztaly
 *
 */
public class Entity {
    private static final Random random = new Random();

    private boolean[] onMap = new boolean[10];
    private String mark;
    private int[][] entities = new int[10][3];

    public Entity(boolean[] onMap, String mark, int[][] entities) {
        this.onMap = onMap;
        this.mark = mark;
        this.entities = entities;
    }

    public Entity() {

    }

    public static Random getRandom() {
        return random;
    }

    public boolean[] getOnMap() {
        return onMap;
    }

    public void setOnMap(boolean[] onMap) {
        this.onMap = onMap;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int[][] getEntities() {
        return entities;
    }

    public void setEntities(int[][] entities) {
        this.entities = entities;
    }
}
