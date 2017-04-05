package me.jessepayne.pad4j.visualizer.swing.comp;

import me.jessepayne.pad4j.core.object.SimpleLaunchpad;
import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;
import me.jessepayne.pad4j.visualizer.swing.clickable.LaunchpadButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VirtualUIPanelScaleable extends JPanel{

    private VirtualUIManager uiManager;

    private boolean initButtons = true;

    private ArrayList<LaunchpadButton> buttons;

    public VirtualUIPanelScaleable(VirtualUIManager uiManager){
        this.uiManager = uiManager;
        this.setLayout(null);

        buttons = new ArrayList<>();


    }

    @Override
    public void paintComponent(Graphics g) {

        int totalButtonSize = getHeight() / 10;
        int spaceBetweenButton = (int) Math.round(totalButtonSize * 0.2);
        totalButtonSize = totalButtonSize - spaceBetweenButton;

        int cursorX = spaceBetweenButton;
        int cursorY = totalButtonSize;

        int buttonX = 0;
        int buttonY = 0;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(3));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        g2d.setColor(uiManager.getThemeManager().getTheme().getBackgroundColor());

        g2d.fillRect(0,0, getWidth(), getHeight());

        while(true){

            if(buttonX >= 9 && buttonY >= 9){
                break;
            }

            if(cursorY >= this.getHeight()){
                cursorX += spaceBetweenButton + totalButtonSize;
                cursorY = totalButtonSize;
                buttonY = 0;
                buttonX++;
                continue;
            }

            Color fillColor = uiManager.getThemeManager().getTheme().getPadOff();
            boolean circle = false;

            if(uiManager.getButtonFromCoords(buttonX, buttonY) != null){
                fillColor = uiManager.getRenderMap().get(SimpleLaunchpad.getCoords(buttonX,buttonY));
            }

            if(buttonX == 0 || buttonX == 9 || buttonY == 0 || buttonY == 9){
                circle = true;
            }

            if(initButtons){
                if(!shouldIgnoreRender(buttonX,buttonY)) {
                    LaunchpadButton button = new LaunchpadButton(uiManager, buttonX, buttonY, cursorX, this.getHeight() - cursorY, totalButtonSize);
                    buttons.add(button);
                    this.add(button);
                }
            }else{
                final int fX = cursorX;
                final int fY = cursorY;
                final int fBx = buttonX;
                final int fBy = buttonY;
                final int fBs = totalButtonSize;

                buttons.stream().filter(b-> fBx == b.getButtonX() && fBy == b.getButtonY()).findFirst().ifPresent(bb -> bb.setBounds(fX, fY - fBs/2 - ((int)(fBs*0.1)), fBs, fBs));
            }

            renderButton(g2d, buttonX, buttonY, cursorX,this.getHeight() - cursorY, circle, fillColor, totalButtonSize);

            cursorY += spaceBetweenButton + totalButtonSize;
            buttonY++;

        }


        initButtons = false;


    }

    public void renderButton(Graphics2D graphics2D, int buttonX, int buttonY, int cursorX, int cursorY, boolean circle, Color color, int size){

        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color);

        //button cutoff
        if(shouldIgnoreRender(buttonX,buttonY)){
            return;
        }

        double highestValue = -1;
        double percentage = 0;

        if(!(color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0)) {
            highestValue = Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
            percentage = (highestValue / 255) * 100;
        }

        if(highestValue != -1) {
            //System.out.println("(" + buttonX + ", " + buttonY + ") - highest value: " + highestValue + " - percentage: " + percentage + "% bright - " + "rgb: (" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")");
        }


        if(!circle) {
            graphics2D.fillRoundRect(cursorX, cursorY, size, size, 5, 5);


            graphics2D.setColor(uiManager.getThemeManager().getTheme().getAccentColor());
            graphics2D.setStroke(new BasicStroke(size / 7));
            graphics2D.drawRect(cursorX, cursorY, size, size);

        }else{
            graphics2D.fillOval(cursorX, cursorY, size, size);
            graphics2D.setColor(uiManager.getThemeManager().getTheme().getAccentColor());
            graphics2D.setStroke(new BasicStroke(size / 7));
            graphics2D.drawOval(cursorX, cursorY, size, size);
        }

    }

    private boolean shouldIgnoreRender(int buttonX, int buttonY){
        return (buttonX == 0 && buttonY == 0) || (buttonX == 0 && buttonY == 9) || (buttonX == 9 && buttonY == 0) || (buttonX == 9 && buttonY == 9);
    }

}
