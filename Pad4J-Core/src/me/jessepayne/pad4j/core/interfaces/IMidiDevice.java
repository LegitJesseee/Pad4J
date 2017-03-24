package me.jessepayne.pad4j.core.interfaces;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;

public interface IMidiDevice {

    void sendMessage(MidiMessage message);

    void sendSysExMessage(byte[] data);

    MidiDevice getOutputDevice() throws MidiUnavailableException;

    MidiDevice getInputDevice() throws MidiUnavailableException;

}
