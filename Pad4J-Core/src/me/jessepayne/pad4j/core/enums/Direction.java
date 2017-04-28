package me.jessepayne.pad4j.core.enums;

/**
 * Created by Jesse on 4/5/17.
 */
public enum Direction {

    NORTH(0,1),
    NORTH_EAST(1,1),
    NORTH_WEST(-1,1),
    SOUTH(0,-1),
    SOUTH_EAST(1,-1),
    SOUTH_WEST(-1,-1),
    EAST(1,0),
    WEST(-1,0);

    private int x;
    private int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getDirX(){
        return x;
    }

    public int getDirY(){
        return y;
    }

}
