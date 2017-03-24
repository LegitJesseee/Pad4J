package me.jessepayne.pad4j.core.enums;

import java.awt.*;

public enum NoteColor {

    RED(72, Color.RED),
    BLUE(68, Color.BLUE),
    GREEN(65, Color.GREEN),
    YELLOW(12, Color.YELLOW),
    WHITE(3, Color.WHITE),
    ORANGE(9, Color.ORANGE),
    PINK(53, Color.PINK),
    PURPLE(48, Color.MAGENTA),
    BLACK(0, Color.BLACK);

    int color;
    Color awtEquivalent;

    NoteColor(int color, Color awtEquivalent){
        this.color = color;
        this.awtEquivalent = awtEquivalent;
    }

    public int getData(){
        return color;
    }

    public NoteColor getNext() {
        NoteColor[] e = NoteColor.values();
        int i = 0;
        for (; e[i] != this; i++)
            ;
        i++;
        i %= e.length;
        return e[i];
    }

    public Color getAwtEquivalent(){
        return awtEquivalent;
    }



}
