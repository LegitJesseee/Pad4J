package me.jessepayne.pad4j.core.midi;

import me.jessepayne.pad4j.core.enums.NoteColor;
import me.jessepayne.pad4j.core.interfaces.IMidiDevice;
import me.jessepayne.pad4j.core.enums.LiveLayout;
import me.jessepayne.pad4j.core.event.LaunchpadListener;
import me.jessepayne.pad4j.core.object.SimpleLaunchpad;

import javax.sound.midi.*;
import java.awt.*;
import java.util.*;

public class LaunchPadPro extends SimpleLaunchpad implements IMidiDevice {

    private final static String UNIX_SIG = "Live Port";

    private static String OS_SIG = "Launchpad Pro";

    private HashMap<Integer,LaunchpadListener> launchpadListeners;

    private MidiDevice inputDevice;
    private MidiDevice outputDevice;

    private Receiver outputReceiver;
    private Transmitter inputTransmitter;
    private Receiver inputReciever;

    public void setup(Runnable callback) {

        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOs = osName.toLowerCase().startsWith("mac");
        if (isMacOs)
        {
            OS_SIG = UNIX_SIG;
        }

        try {
            inputDevice = getInputDevice();
            outputDevice = getOutputDevice();
        } catch (MidiUnavailableException e) {
            System.out.println("FATAL: Error receiving device master list...");
            System.exit(0);
        }

        launchpadListeners = new HashMap<>();


        if(inputDevice == null || outputDevice == null){

            if(inputDevice == null){
                System.out.println("Input device not found.");
            }

            if(outputDevice == null){
                System.out.println("Output device not found.");
            }

            System.out.println("Could not detect device. Is it turned on?");

            System.exit(0);

        }

        try {

            if (outputDevice != null && inputDevice != null) {

                System.out.println("Successfully connected to " + OS_SIG + "!");

                System.out.println();
                System.out.println("Checking Output Status: ");
                if (!outputDevice.isOpen()) {
                    System.out.println("outputDevice not open... opening...");
                    outputDevice.open();
                } else {
                    System.out.println("outputDevice is already open!");
                }

                outputReceiver = outputDevice.getReceiver();

                System.out.println();
                System.out.println("Checking Input Status: ");
                if (!inputDevice.isOpen()) {
                    System.out.println("inputDevice not open... opening...");
                    inputDevice.open();
                } else {
                    System.out.println("inputDevice is already open!");
                }


                inputReciever = new LaunchpadProListenerReceiver(this);
                inputTransmitter = inputDevice.getTransmitter();
                inputTransmitter.setReceiver(inputReciever);

                System.out.println();
                if (outputReceiver == null || inputTransmitter == null) {
                    System.out.println("A Transmitter/Receiver is null...");
                    System.exit(0);
                } else {
                    System.out.println("Transmitter/Receiver setup! :D");
                }

            }
        }catch(MidiUnavailableException e){
            System.out.println("FATAL: Error while initializing device...");
            System.exit(0);
        }


        if (callback != null) {
            callback.run();
        }

    }

    public MidiDevice getOutputDevice() throws MidiUnavailableException {
        MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : midiDeviceInfo) {

            System.out.println(" ");
            System.out.println("=============== OUTPUT DEVICE (TO SEND TO) ===============");

            System.out.println("Name: " + info.getName());
            System.out.println("Description: " + info.getDescription());
            System.out.println("Version: " + info.getVersion());
            System.out.println("Vendor: " + info.getVendor());
            System.out.println();
            System.out.println("OS Sig: " + OS_SIG);
            System.out.println("=====================================");

            if (info.getDescription().toLowerCase().contains(OS_SIG.toLowerCase()) || info.getName().toLowerCase().contains(OS_SIG.toLowerCase())) {
                MidiDevice device = MidiSystem.getMidiDevice(info);
                if (device.getMaxReceivers() == -1) {
                    System.out.println();
                    System.out.println(">>>>> Output device found: " + device.getDeviceInfo().getName());
                    System.out.println();
                    return device;
                }
            }
        }
        return null;
    }

    public MidiDevice getInputDevice() throws MidiUnavailableException {
        MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : midiDeviceInfo) {

            System.out.println(" ");
            System.out.println("=============== INPUT DEVICE (TO RECEIVE FROM) ===============");

            System.out.println("Name: " + info.getName());
            System.out.println("Description: " + info.getDescription());
            System.out.println("Version: " + info.getVersion());
            System.out.println("Vendor: " + info.getVendor());
            System.out.println();
            System.out.println("OS Sig: " + OS_SIG);
            System.out.println("=====================================");

            if (info.getDescription().toLowerCase().contains(OS_SIG.toLowerCase()) || info.getName().toLowerCase().contains(OS_SIG.toLowerCase())) {
                MidiDevice device = MidiSystem.getMidiDevice(info);
                if (device.getMaxTransmitters() == -1) {
                    System.out.println();
                    System.out.println(">>>>> Input device found: " + device.getDeviceInfo().getName());
                    System.out.println();

                    return device;
                }
            }
        }
        return null;
    }

    @Deprecated
    public void noteOn(int note, NoteColor color){

        ShortMessage message;
        try {
            message = new ShortMessage(ShortMessage.NOTE_ON, (byte) note, (byte) color.getData());
        } catch (InvalidMidiDataException e) {
            System.out.println("Error generating Midi Message: " + e.getMessage());
            return;
        }

        outputReceiver.send(message, -1);
    }


    public void noteOff(int note){

        if((inputDevice == null || outputDevice == null)){
            System.out.println("Could not deliver message because there is no route to midi device.");
            return;
        }

        try {
            outputReceiver.send(new ShortMessage(ShortMessage.NOTE_OFF, note, 0), -1);
        } catch (InvalidMidiDataException e) {
            System.out.println("Error generating Midi Message: " + e.getMessage());
            return;
        }
    }

    public void clearScreen() {
        for(int i = 0; i <= 98; i++){
            noteOff(i);
        }
    }

    public void noteOn(int note, int r, int g, int b){
        byte[] data = new byte[] {(byte) /* header */ 0xF0, 0x00, 0x20, 0x29, 0x02,0x10, /* cmd */ 0x0B, /* led */ (byte) note, /* rgb */ (byte) r, (byte) g, (byte) b, /* footer */ (byte) 0xF7};
        sendSysExMessage(data);
    }

    public void noteOn(int note, Color color){
        noteOn(note, Math.round(color.getRed() / 4), Math.round(color.getGreen() / 4), Math.round(color.getBlue() / 4));
    }

    public void text(NoteColor color, String text, boolean loop){

        ArrayList<Byte> bytes = new ArrayList<>();

        //header
        bytes.add((byte) 0xF0);
        bytes.add((byte) 0x00);
        bytes.add((byte) 0x20);
        bytes.add((byte) 0x29);
        bytes.add((byte) 0x02);
        bytes.add((byte) 0x10);

        //cmd
        bytes.add((byte) 0x14);

        //color
        bytes.add((byte) color.getData());

        //loop
        bytes.add((byte) (loop ? 1 : 0));

        //text
        for(char c : text.toCharArray()){
            bytes.add((byte) c);
        }

        byte[] raw = new byte[bytes.size()];

        for(int i = 0; i < bytes.size(); i++){
            raw[i] = bytes.get(i);
        }

        sendSysExMessage(raw);

    }

    public void pulseLed(int note, NoteColor color){
        byte[] data = new byte[] {(byte) /* header */ 0xF0, 0x00, 0x20, 0x29, 0x02,0x10, /* cmd */ 0x28, /* led */ (byte) note, /* color */ (byte) color.getData(), /* footer */ (byte) 0xF7};
        sendSysExMessage(data);
    }

    public void flashLed(int note, NoteColor color){
        byte[] data = new byte[] {(byte) /* header */ 0xF0, 0x00, 0x20, 0x29, 0x02,0x10, /* cmd */ 0x23, /* led */ (byte) note, /* color */ (byte) color.getData(), /* footer */ (byte) 0xF7};
        sendSysExMessage(data);
    }

    public void setLiveLayout(LiveLayout layout){
        byte[] data = new byte[] {(byte) /* header */ 0xF0, 0x00, 0x20, 0x29, 0x02,0x10, /* cmd */ 0x22, /* layout */ layout.getData(), /* footer */ (byte) 0xF7};
        sendSysExMessage(data);
    }

    private Timer midiPacketTimer = new Timer();
    private MidiPacketTask curTask = new MidiPacketTask(this);

    public void setMIDIClock(int bpm){
        if(curTask.isRunning) {
            midiPacketTimer.cancel();
            if(bpm == -1 || bpm == 0){
                System.out.println("Cancelled MIDI BPM");
                return;
            }
        }

        if(bpm > 240 || bpm < 40){
            System.out.println("Invalid BPM. [40-240]");
            return;
        }

        curTask.setBPM(bpm);
        midiPacketTimer.scheduleAtFixedRate(curTask, 0, 60000 / bpm);

    }

    public void sendMessage(MidiMessage message){
        for(LaunchpadListener listener : getLaunchpadListeners().values()) {
            if(message instanceof ShortMessage) {
                listener.onShortMessageSent(message);
            } else if (message instanceof SysexMessage){
                listener.onSysExMessageSent((SysexMessage) message);
            }
        }


        if(outputDevice == null || inputDevice == null){
            System.out.println("Could not deliver message because there is no connection to a midi device, and allowPseudoConnection is enabled.");
            return;
        }

            outputReceiver.send(message, -1);
    }


    public void sendSysExMessage(byte[] data){
        SysexMessage message = new SysexMessage();
        try {
            message.setMessage(data, data.length);
        } catch (InvalidMidiDataException e) {
            System.out.println("ERROR: Invalid MIDI Packet: " + e.getMessage());
        }

        sendMessage(message);
    }

    protected class MidiPacketTask extends TimerTask {

        private boolean isRunning = false;
        private LaunchPadPro launchPad;
        private int bpm;

        public MidiPacketTask(LaunchPadPro launchPad){
            this.launchPad = launchPad;
        }

        public LaunchPadPro getLaunchPad(){
            return launchPad;
        }

        public int getBPM(){
            return bpm;
        }

        public void setBPM(int bpm){
            this.bpm = bpm;
        }

        public boolean isRunning(){
            return isRunning;
        }

        @Override
        public void run() {
            isRunning = true;
            byte[] data = new byte[] {(byte) /* header */ 0xF0, 0x00, 0x20, 0x29, 0x02,0x10, /* cmd */ (byte) 0xF8, /* footer */ (byte) 0xF7};
                sendSysExMessage(data);
        }
    }



}
