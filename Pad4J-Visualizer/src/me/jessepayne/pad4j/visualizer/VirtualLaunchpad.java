package me.jessepayne.pad4j.visualizer;

import me.jessepayne.pad4j.core.enums.NoteColor;
import me.jessepayne.pad4j.core.event.LaunchpadListener;
import me.jessepayne.pad4j.core.object.*;
import me.jessepayne.pad4j.core.object.Button;
import me.jessepayne.pad4j.visualizer.swing.LaunchpadLAF;
import me.jessepayne.pad4j.visualizer.swing.VirtualUIManager;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class VirtualLaunchpad extends SimpleLaunchpad {

    public static void main(String... args){

        VirtualLaunchpad launchPad = new VirtualLaunchpad();
        launchPad.setup(() -> {

            launchPad.noteOn(getCoords(0,0), Color.BLUE );
            launchPad.noteOn(getCoords(1,0), Color.RED);

            launchPad.getPulseLedTask().add(SimpleLaunchpad.getButton(SimpleLaunchpad.getCoords(5,5)), NoteColor.GREEN);

            launchPad.registerListener(new LaunchpadListener() {
                @Override
                public void onButtonPushed(Button button, long timestamp) {
                    System.out.println("MEMES");
                }
            });

        });

    }

    private PulseLedTask pulseLedTask;
    private VirtualUIManager virtualUIManager;

    @Override
    public void setup(Runnable runnable) {


        try {
            UIManager.setLookAndFeel(new LaunchpadLAF());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        pulseLedTask = new PulseLedTask(this);
        virtualUIManager = new VirtualUIManager(this);

        Timer pulseTimer = new Timer();
        pulseTimer.scheduleAtFixedRate(pulseLedTask, 0, 500);

        virtualUIManager.setup(new JFrame(), false);

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
        virtualUIManager.getRenderMap().clear();
        virtualUIManager.update();
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
