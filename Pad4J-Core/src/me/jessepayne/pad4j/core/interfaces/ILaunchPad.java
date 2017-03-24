package me.jessepayne.pad4j.core.interfaces;

import me.jessepayne.pad4j.core.enums.NoteColor;
import me.jessepayne.pad4j.core.event.LaunchpadListener;

import java.awt.*;

public interface ILaunchPad {

        void setup(Runnable runnable);

        void noteOff(int note);

        void clearScreen();

        void noteOn(int note, int r, int g, int b);

        void noteOn(int note, Color color);

        void text(NoteColor color, String text, boolean loop);

        void pulseLed(int note, NoteColor color);

        void flashLed(int note, NoteColor color);

        int registerListener(LaunchpadListener launchpadListener);

        boolean unregisterListener(int id);

}
