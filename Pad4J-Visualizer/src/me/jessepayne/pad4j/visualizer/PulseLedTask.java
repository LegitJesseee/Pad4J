package me.jessepayne.pad4j.visualizer;

import me.jessepayne.pad4j.core.object.Button;
import me.jessepayne.pad4j.core.enums.NoteColor;

import java.util.HashMap;
import java.util.TimerTask;

public class PulseLedTask extends TimerTask {

    boolean on = false;

    private HashMap<Button, NoteColor> toPulse;
    private VirtualLaunchpad device;

    public PulseLedTask(VirtualLaunchpad device){
        this.device = device;
        toPulse = new HashMap<>();
    }

    @Override
    public void run() {

        on = !on;

        if(on){
            for(Button b : toPulse.keySet()){
                device.noteOn(device.getCoords(b), toPulse.get(b).getAwtEquivalent());
            }
        }else{
            for(Button b : toPulse.keySet()){
                device.noteOff(device.getCoords(b));
            }
        }
    }

    public void remove(Button b){
        toPulse.remove(b);
        device.noteOff(device.getCoords(b));
    }

    public void add(Button b, NoteColor c) {
        toPulse.put(b, c);
    }

}
