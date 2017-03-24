package me.jessepayne.pad4j.visualizer.swing.theme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeMenuAction implements ActionListener {

    public ThemeMenuAction(ThemeManager themeManager, LPTheme theme) {
        this.themeManager = themeManager;
        this.theme = theme;
    }

    private ThemeManager themeManager;
    private LPTheme theme;


    @Override
    public void actionPerformed(ActionEvent e) {
        themeManager.fadeTheme(theme);
    }
}
