package me.jessepayne.pad4j.core.object;

import me.jessepayne.pad4j.core.enums.ButtonStatus;
import me.jessepayne.pad4j.core.enums.CCButtonEnum;
import me.jessepayne.pad4j.core.midi.LaunchPadPro;

import java.awt.*;

public class Button {

    private ButtonStatus status = ButtonStatus.OFF;
    private Color color = new Color(0,0,0);

    private int x;
    private int y;

    private boolean isPad = true;

    public Button(int x, int y){
        this.x = x;
        this.y = y;

        for(CCButtonEnum button : CCButtonEnum.values()){
            if(LaunchPadPro.getCoords(x,y) == button.getNote()){
                isPad = false;
                break;
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o == null || o.getClass() != getClass()){
            return false;
        }
        Button b = (Button) o;
        return this.x == b.getX() && this.y == b.getY();
    }

    public boolean isPad(){
        return isPad;
    }

    public boolean isButton(){
        return !isPad;
    }

    public CCButtonEnum getButtonEnum(){
        if(isPad){
            return null;
        }
        return null;
    }

    @Override
    public int hashCode() {
        return (y*10) + x;
    }


    @Override
    public String toString(){
        return "Pad[" + x + "," + y + "]";
    }


    public void setStatus(Button button, ButtonStatus status, int... colorData){
        status = ButtonStatus.OFF;
    }

    public ButtonStatus getStatus(){
        return status;
    }

}
