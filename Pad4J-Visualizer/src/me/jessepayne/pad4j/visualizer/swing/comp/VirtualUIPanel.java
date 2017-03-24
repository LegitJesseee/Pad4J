package me.jessepayne.pad4j.visualizer.swing.comp;

import me.jessepayne.pad4j.core.object.SimpleLaunchpad;
import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;

import javax.swing.*;
import java.awt.*;

public class VirtualUIPanel extends JComponent{

    private VirtualUIManager uiManager;

    public VirtualUIPanel(VirtualUIManager uiManager){
        this.uiManager = uiManager;
    }

  /*  @Override
    public void  repaint(Graphics g){
        paint(g);
    }*/

    @Override
    public void paintComponent(Graphics g) {
        int cursorX = 25;
        int cursorY = 25;

        int buttonX = 0;
        int buttonY = 0;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(3));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        g2d.setColor(new Color(235,100,18));

        /*if(firstRender) {
            g2d.fillRect(0, 0, 775, 805);
        }*/


        while(true){

            if(cursorX >= 750){
                break;
            }

            if(cursorY >= 750){
                cursorX += 75;
                cursorY = 25;
                buttonY = 0;
                buttonX++;
                continue;
            }

            Color fillColor = Color.BLACK;
            boolean circle = false;

            if(uiManager.getButtonFromCoords(buttonX, buttonY) != null){
                fillColor = uiManager.getRenderMap().get(SimpleLaunchpad.getCoords(buttonX,buttonY));
            }

            if(buttonX == 0 || buttonX == 9 || buttonY == 0 || buttonY == 9){
                circle = true;
            }

            renderButton(g2d, buttonX, buttonY, cursorX, 725 - cursorY, circle, fillColor);

            cursorY += 75;
            buttonY++;

        }



    }

    public void renderButton(Graphics2D graphics2D, int buttonX, int buttonY, int cursorX, int cursorY, boolean circle, Color color){

        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color);

        //button cutoff
        if((buttonX == 0 && buttonY == 0) || (buttonX == 0 && buttonY == 9) || (buttonX == 9 && buttonY == 0) || (buttonX == 9 && buttonY == 9)) {
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
            graphics2D.fillRoundRect(cursorX, cursorY, 50, 50, 5, 5);
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.drawRect(cursorX, cursorY, 50, 50);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.drawRoundRect(cursorX - 2, cursorY - 2, 54, 54, 10, 10);
            //graphics2D.setColor(Color.orange);
            //graphics2D.drawString(String.valueOf(Math.round(percentage)) + "%", cursorX + 5, cursorY + 25);

        }else{
            graphics2D.fillOval(cursorX, cursorY, 50, 50);
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.drawOval(cursorX, cursorY, 50, 50);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.drawOval(cursorX - 2, cursorY - 2, 54, 54);
        }

    }

}
