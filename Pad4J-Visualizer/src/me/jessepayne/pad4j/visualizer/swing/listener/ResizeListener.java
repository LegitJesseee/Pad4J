package me.jessepayne.pad4j.visualizer.swing.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter {

    public ResizeListener(JFrame frame){
        curHeight = frame.getHeight();
        curWidth = frame.getWidth();
    }

    int curHeight;
    int curWidth;

    @Override
    public void componentResized(ComponentEvent e) {

        int newHeight = e.getComponent().getHeight();
        int newWidth = e.getComponent().getWidth();

        if((newHeight == newWidth) || (curHeight == newHeight && curWidth == newWidth)){
            System.out.println("No reason to do anything.");
            return;
        }

        int size = Math.max(newHeight, newWidth);

        e.getComponent().setSize(new Dimension(size,size));
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

}
