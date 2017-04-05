package me.jessepayne.pad4j.core.object;

import me.jessepayne.pad4j.core.event.LaunchpadListener;
import me.jessepayne.pad4j.core.interfaces.ILaunchPad;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public abstract class SimpleLaunchpad implements ILaunchPad {

    private HashMap<Integer,LaunchpadListener> launchpadListeners = new HashMap<>();
    private HashMap<Button, Color> colorMap = new HashMap<>();

    public int registerListener(LaunchpadListener launchpadListener){
        int rand;
        while(true){
            rand = new Random().nextInt(10000);
            if(launchpadListeners.containsKey(rand)){
                continue;
            }else{
                launchpadListeners.put(rand, launchpadListener);
                System.out.println(launchpadListener.getClass().getSimpleName() + " registered.");
                break;
            }
        }
        return rand;
    }

    public boolean unregisterListener(int id){
        if(launchpadListeners.containsKey(id)){
            launchpadListeners.remove(id);
            return true;
        }
        return false;
    }

    public HashMap<Integer,LaunchpadListener> getLaunchpadListeners(){
        return launchpadListeners;
    }

    public static int getCoords(int x, int y){
        return y > 9 || x > 9 ? 0 : (y*8) + (y*2) + x;
    }

    public static int getCoords(Button button){
        return getCoords(button.getX(), button.getY());
    }

    public static Button getButton(int note){
        int x = note%10;
        return new Button(x, (note-x) / 10);

    }


}
