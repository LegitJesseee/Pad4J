package me.jessepayne.pad4j.visualizer.swing.theme;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class ThemeChangeTask extends TimerTask {

    private ThemeManager themeManager;

    private LPTheme startTheme;
    private LPTheme endTheme;

    private ArrayList<LPTheme> steps;

    private Runnable endTask;

    private int stepCount;
    private int curStep = 1;

    public ThemeChangeTask(ThemeManager themeManager, int stepCount, LPTheme startTheme, LPTheme endTheme){
        this.themeManager = themeManager;
        this.stepCount = stepCount;
        this.startTheme = startTheme;
        this.endTheme = endTheme;
        setup();

    }

    public void setup(){

        System.out.println("---");

        steps = new ArrayList<>();

        ArrayList<Color> backgroundsColors = generateColors(startTheme.getBackgroundColor(), endTheme.getBackgroundColor());
        ArrayList<Color> accentColors = generateColors(startTheme.getAccentColor(), endTheme.getAccentColor());
        for(int i = 0; i < backgroundsColors.size() - 1; i++){
            steps.add(new LPTheme(backgroundsColors.get(i), accentColors.get(i)));
        }
    }

    public ThemeChangeTask(ThemeManager themeManager, LPTheme startTheme, LPTheme endTheme){
        this(themeManager, 10, startTheme, endTheme);
    }

    @Override
    public void run() {
        if(curStep >= stepCount){
            cancel();
            themeManager.setIsFading(false);

            if(endTask != null){
                endTask.run();
            }else{
                themeManager.setTheme(endTheme);
            }

            return;
        }

        /*Color bc = endTheme.getBackgroundColor();
        Color lc = steps.get(curStep-1).getBackgroundColor();

        int avgBC = ((bc.getRed() + bc.getGreen() + bc.getBlue()) / 3);
        int avgLC = ((lc.getRed() + lc.getGreen() + lc.getBlue())/3);

        int avg = Math.max(avgBC, avgLC) - Math.min(avgBC, avgLC);
        System.out.println(avg + " avg units from final color.");*/

        themeManager.setTheme(steps.get(curStep - 1));
        curStep++;
    }


    public ArrayList<Color> generateColors(Color oldColor, Color newColor){

        ArrayList<Color> colors = new ArrayList<>();

        colors.add(oldColor);

        int rDelta = ( oldColor.getRed() - newColor.getRed() ) / stepCount;
        int gDelta = ( oldColor.getGreen() - newColor.getGreen() ) / stepCount;
        int bDelta = ( oldColor.getBlue() - newColor.getBlue() ) / stepCount;

        for (int i = 1; i < stepCount; i++)
        {
            int rValue = oldColor.getRed() - (i * rDelta);
            int gValue = oldColor.getGreen() - (i * gDelta);
            int bValue = oldColor.getBlue() - (i * bDelta);

            colors.add( new Color(rValue, gValue, bValue) );
        }


        double ratio = 0;

        //Out = C0 + (C1-C0) * t

        for(int i = 0; i <= stepCount; i++){

            ratio = (1.0/stepCount)*i;

           /* int r = ((int)(oldColor.getRed() + (newColor.getRed() - oldColor.getRed()) * ratio));
            int g = ((int)(oldColor.getGreen() + (newColor.getGreen() - oldColor.getGreen()) * ratio));
            int b = ((int)(oldColor.getBlue() + (newColor.getBlue() - oldColor.getBlue()) * ratio));
            */
            int r = (int)Math.abs((ratio * newColor.getRed()) + ((1 - ratio) * oldColor.getRed()));
            int g = (int)Math.abs((ratio * newColor.getGreen()) + ((1 - ratio) * oldColor.getGreen()));
            int b = (int)Math.abs((ratio * newColor.getBlue()) + ((1 - ratio) * oldColor.getBlue()));

            System.out.println("(" + r + ", " + g + ", " + b + ") (" + Math.round(ratio * 100) + "% complete.)");
            colors.add(new Color(r,g,b));
        }

        return colors;
    }


    public void setEndTask(Runnable endTask){
        this.endTask = endTask;
    }

    public ArrayList<LPTheme> getSteps(){
        return steps;
    }


}
