package me.jessepayne.pad4j.visualizer.swing.theme;

import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;

import java.util.Timer;

public class ThemeManager {

    private VirtualUIManager uiManager;
    private LPTheme theme = LPTheme.AQUA;

    private Timer fadeTimer = new Timer();
    private boolean fadeRunning = false;

    public ThemeManager(VirtualUIManager uiManager){
        this.uiManager = uiManager;
    }

    public VirtualUIManager getUiManager(){
        return uiManager;
    }

    public LPTheme getTheme(){
        return theme;
    }

    public boolean fadeTheme(LPTheme newTheme){
        if(fadeRunning){
            System.out.println("Fade is already running!");
            return false;
        }else{

            if(newTheme.equals(theme)){
                System.out.println("You can't fade to the theme that is already selected!");
                return false;
            }

            ThemeChangeTask task = new ThemeChangeTask(this, 30, theme, newTheme);
            fadeTimer.scheduleAtFixedRate(task, 0L, 50L);
            return true;
        }
    }

    public void setTheme(LPTheme theme){
        this.theme = theme;
        uiManager.update();
    }

    public boolean switchTheme(LPTheme theme){
        if(fadeRunning){
            System.out.println("Cannot switch theme while fade is running!");
            return false;
        }else{
            setTheme(theme);
            return true;
        }
    }

    public void setIsFading(boolean isFading){
        fadeRunning = isFading;
    }

    public boolean isFadeRunning(){
        return fadeRunning;
    }

}
