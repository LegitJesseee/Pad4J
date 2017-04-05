package me.jessepayne.pad4j.visualizer.swing.clickable;

import me.jessepayne.pad4j.visualizer.swing.theme.LPTheme;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Jesse on 3/24/17.
 */
public class LaunchpadButtonRenderer extends BasicButtonUI {

    @Override
    public void paint(Graphics g, JComponent c){

        Graphics2D g2d = (Graphics2D) g;

        JButton button = (JButton) c;

        Color orig = LPTheme.RED.getAccentColor();
        g2d.setColor(Color.RED);
        g2d.fillRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
        g.setColor(Color.BLACK);
        g2d.drawString(button.getText(), c.getX() + (c.getWidth()/2), c.getY() + (c.getHeight()/2));
    }


}
