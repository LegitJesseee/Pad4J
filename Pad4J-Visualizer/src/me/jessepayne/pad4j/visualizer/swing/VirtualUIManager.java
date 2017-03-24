package me.jessepayne.pad4j.visualizer.swing;

import me.jessepayne.pad4j.core.object.*;
import me.jessepayne.pad4j.core.object.Button;
import me.jessepayne.pad4j.visualizer.swing.comp.VirtualUIPanelScaleable;
import me.jessepayne.pad4j.visualizer.swing.theme.LPTheme;
import me.jessepayne.pad4j.visualizer.swing.theme.ThemeManager;
import me.jessepayne.pad4j.visualizer.swing.theme.ThemeMenuAction;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

public class VirtualUIManager {

    private ConcurrentHashMap<Integer, Color> renderMap = new ConcurrentHashMap<>();

    private JFrame frame;
    private VirtualUIPanelScaleable panel;

    private ThemeManager themeManager;

    public void setup(){
        frame = new JFrame();

        //frame.setSize(775,805);
        frame.setSize(600,600);
        frame.setTitle("Virtual Launchpad Pro");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JMenuBar jMenuBar = new JMenuBar();
        JMenu themeMenu = new JMenu("Theme");

        themeManager = new ThemeManager(this);

        ButtonGroup buttonGroup = new ButtonGroup();

        for(LPTheme theme : LPTheme.getStandardThemes()){

            if(theme == null){
                continue;
            }

            JRadioButtonMenuItem item = new JRadioButtonMenuItem(theme.getName());
            item.addActionListener(new ThemeMenuAction(themeManager, theme));
            themeMenu.add(item);
            buttonGroup.add(item);
        }

        System.out.println(buttonGroup.getSelection());

        jMenuBar.add(themeMenu);
        frame.setJMenuBar(jMenuBar);

        System.out.println(frame.getBaselineResizeBehavior());

        panel = new VirtualUIPanelScaleable(this);
        //frame.addComponentListener(new ResizeListener(frame));
        frame.add(panel);
        frame.setMinimumSize(new Dimension(125,150));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public me.jessepayne.pad4j.core.object.Button getButtonFromCoords(int x, int y){

        for(Integer i : renderMap.keySet()){

            Button b = SimpleLaunchpad.getButton(i);

            if(b.getX() == x && b.getY() == y){
                return b;
            }
        }
        return null;
    }

    public void update(){
        panel.repaint();
    }

    public ConcurrentHashMap<Integer,Color> getRenderMap(){
        return renderMap;
    }

    public ThemeManager getThemeManager(){
        return themeManager;
    }

}
