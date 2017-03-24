package me.jessepayne.pad4j.core.event;

import me.jessepayne.pad4j.core.object.Button;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.SysexMessage;

public abstract class LaunchpadListener {

    public void onButtonPushed(Button button, long timestamp){
        System.out.println("onButtonPushed() not implemented.");
    }

    public void onButtonReleased(Button button, long timestamp){
        System.out.println("onButtonReleased() not implemented.");
    }

    public void onShortMessageSent(MidiMessage message){
        System.out.println("onMidiMessageSent() not implemented.");
    }

    public void onSysExMessageSent(SysexMessage sysexMessage){
        System.out.println("onSysExMessageSent() not implemented.");
    }

    public void onMidiMessageReceived(MidiMessage message) {
        System.out.println("onMidiMessageReceived() not implemented.");
    }

}
