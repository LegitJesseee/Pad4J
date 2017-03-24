package me.jessepayne.pad4j.visualizer.swing.theme;

import java.awt.*;
import java.util.ArrayList;

public class LPTheme {

    private static final ArrayList<LPTheme> colors = new ArrayList<>();

    public static final LPTheme AQUA = new LPTheme("Aqua", new Color(26, 188, 156), new Color(22, 160, 133));
    public static final LPTheme GREEN = new LPTheme("Green", new Color(46, 204, 113), new Color(39, 174, 96));
    public static final LPTheme BLUE = new LPTheme("Blue", new Color(52, 152, 219), new Color(41, 128, 185));
    public static final LPTheme PURPLE = new LPTheme("Purple", new Color(155, 89, 182), new Color(142, 68, 173));
    public static final LPTheme DARK_BLUE = new LPTheme("Dark Blue", new Color(52, 73, 94), new Color(44, 62, 80));
    public static final LPTheme YELLOW = new LPTheme("Yellow", new Color(241, 196, 15), new Color(243, 156, 18));
    public static final LPTheme ORANGE = new LPTheme("Orange", new Color(230, 126, 34), new Color(211, 84, 0));
    public static final LPTheme RED = new LPTheme("Red", new Color(231, 76, 60), new Color(192, 57, 43));
    public static final LPTheme WHITE = new LPTheme("White", new Color(236, 240, 241), new Color(189, 195, 199));
    public static final LPTheme GRAY = new LPTheme("Gray", new Color(149, 165, 166), new Color(127, 140, 141));

    static{
        colors.add(AQUA);
        colors.add(GREEN);
        colors.add(BLUE);
        colors.add(PURPLE);
        colors.add(DARK_BLUE);
        colors.add(YELLOW);
        colors.add(ORANGE);
        colors.add(RED);
        colors.add(WHITE);
        colors.add(GRAY);
    }

    private String name;

    private Color backgroundColor;
    private Color accentColor;
    private Color padOff;
    private Color textColor;

    public LPTheme(String name, Color backgroundColor, Color accentColor){
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.accentColor = accentColor;
        this.padOff = new Color(44, 62, 80);
        this.textColor = padOff;

    }

    public LPTheme (Color backgroundColor, Color accentColor){
        this("CUSTOM", backgroundColor, accentColor);
    }

    public String getName(){
        return name;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public Color getAccentColor(){
        return accentColor;
    }

    public Color getPadOff(){
        return padOff;
    }

    public Color getTextColor(){
        return textColor;
    }

    public static ArrayList<LPTheme> getStandardThemes(){
        return colors;
    }

}
