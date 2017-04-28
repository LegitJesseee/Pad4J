package me.jessepayne.pad4j.core.object;

import me.jessepayne.pad4j.core.enums.ButtonStatus;
import me.jessepayne.pad4j.core.enums.ButtonType;
import me.jessepayne.pad4j.core.enums.Direction;
import me.jessepayne.pad4j.core.interfaces.IButton;

import java.awt.*;

/**
 * Created by Jesse on 4/5/17.
 */
public abstract class AbstractButton implements IButton {

    private int x;
    private int y;

    private ButtonStatus status;
    private ButtonType buttonType;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ButtonStatus getStatus() {
        return null;
    }

    @Override
    public ButtonType getType() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public IButton getAdjacent(Direction direction, int... multiplier) {
        return null;
    }

    @Override
    public boolean isValid(){
        return true;
    }


    @Override
    public IButton setStatus(ButtonStatus status) {
        return null;
    }

    @Override
    public IButton setOpacity(float opacity) {
        return null;
    }

    @Override
    public IButton setColor(Color c) {
        return null;
    }

}
