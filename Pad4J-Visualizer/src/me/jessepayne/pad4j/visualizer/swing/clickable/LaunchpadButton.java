package me.jessepayne.pad4j.visualizer.swing.clickable;

import me.jessepayne.pad4j.core.event.LaunchpadListener;
import me.jessepayne.pad4j.core.object.Button;
import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class LaunchpadButton extends JButton {

    private int buttonX;
    private int buttonY;

    private int coordsX;
    private int coordsY;

    private VirtualUIManager uiManager;

    public LaunchpadButton(VirtualUIManager uiManager, int buttonX, int buttonY, int coordsX, int coordsY, int size){

        super();

        this.uiManager = uiManager;

        this.buttonX = buttonX;
        this.buttonY = buttonY;

        this.coordsX = coordsX;
        this.coordsY = coordsY;

        this.setBounds(coordsX,coordsY,size,size);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setUI(new BasicButtonUI(){

        });

        this.addActionListener(e -> {

            System.out.println("(" + buttonX + ", " + (9 - buttonY) + ")");

            for(LaunchpadListener listener : uiManager.getLaunchpad().getLaunchpadListeners().values()){
                listener.onButtonPushed(new Button(buttonX, (9 - buttonY)), System.currentTimeMillis());
            }

        });

        this.setVisible(true);
    }

    public void setButtonX(int buttonX) {
        this.buttonX = buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public void setButtonY(int buttonY) {
        this.buttonY = buttonY;
    }

    public int getCoordsX() {
        return coordsX;
    }

    public void setCoordsX(int coordsX) {
        this.coordsX = coordsX;
    }

    public int getCoordsY() {
        return coordsY;
    }

    public void setCoordsY(int coordsY) {
        this.coordsY = coordsY;
    }

    public int getButtonX(){
        return buttonX;

    }

}
