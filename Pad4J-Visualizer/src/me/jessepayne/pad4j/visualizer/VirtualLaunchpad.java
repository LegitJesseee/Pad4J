package me.jessepayne.pad4j.visualizer;

import me.jessepayne.pad4j.core.enums.NoteColor;
import me.jessepayne.pad4j.core.object.SimpleLaunchpad;
import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;

import java.awt.*;
import java.util.Timer;

public class VirtualLaunchpad extends SimpleLaunchpad {

    public static void main(String... args){

        VirtualLaunchpad launchPad = new VirtualLaunchpad();
        launchPad.setup(() -> {

            launchPad.noteOn(getCoords(0,0), Color.BLUE );
            launchPad.noteOn(getCoords(1,0), Color.RED);

            launchPad.getPulseLedTask().add(SimpleLaunchpad.getButton(SimpleLaunchpad.getCoords(5,5)), NoteColor.GREEN);



        });

    }

    private PulseLedTask pulseLedTask;
    private VirtualUIManager virtualUIManager;

    @Override
    public void setup(Runnable runnable) {
        pulseLedTask = new PulseLedTask(this);
        virtualUIManager = new VirtualUIManager();

        Timer pulseTimer = new Timer();
        pulseTimer.scheduleAtFixedRate(pulseLedTask, 0, 500);

        virtualUIManager.setup();

        if(runnable != null) {
            runnable.run();
        }
    }

    @Override
    public void noteOff(int note) {
        virtualUIManager.getRenderMap().remove(note);
        virtualUIManager.update();
    }

    @Override
    public void clearScreen() {

    }

    @Override
    public void noteOn(int note, int r, int g, int b) {
        noteOn(note, new Color(r,g,b));
    }

    @Override
    public void noteOn(int note, Color color) {
        virtualUIManager.getRenderMap().put(note, color);
        virtualUIManager.update();
    }

    @Override
    public void text(NoteColor color, String text, boolean loop) {

    }

    @Override
    public void pulseLed(int note, NoteColor color) {
        pulseLedTask.add(getButton(note), color);
    }

    @Override
    public void flashLed(int note, NoteColor color) {
        pulseLed(note, color);
    }

    public PulseLedTask getPulseLedTask(){
        return pulseLedTask;
    }

    public VirtualUIManager getVirtualUIManager(){
        return virtualUIManager;
    }

}
