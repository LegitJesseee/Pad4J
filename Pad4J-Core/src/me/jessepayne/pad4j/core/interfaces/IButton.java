package me.jessepayne.pad4j.core.interfaces;

import me.jessepayne.pad4j.core.enums.ButtonStatus;
import me.jessepayne.pad4j.core.enums.ButtonType;
import me.jessepayne.pad4j.core.enums.Direction;

import java.awt.*;

/**
 * Created by Jesse on 4/5/17.
 */
public interface IButton {

    public int getX();

    public int getY();

    public ButtonStatus getStatus();

    public ButtonType getType();

    public Color getColor();

    public IButton getAdjacent(Direction direction, int... multiplier);

    public boolean isValid();

    // Chain setters

    public IButton setStatus(ButtonStatus status);

    public IButton setOpacity(float opacity);

    public IButton setColor(Color c);

}
