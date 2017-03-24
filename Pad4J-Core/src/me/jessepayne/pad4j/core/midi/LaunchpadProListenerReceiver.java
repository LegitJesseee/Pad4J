package me.jessepayne.pad4j.core.midi;

import me.jessepayne.pad4j.core.event.LaunchpadListener;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class LaunchpadProListenerReceiver implements Receiver {

    private LaunchPadPro launchPad;

    public LaunchpadProListenerReceiver(LaunchPadPro launchPad){
        this.launchPad = launchPad;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage){
            handleShortMessage((ShortMessage) message, timeStamp);
        }
    }

    protected void handleShortMessage(ShortMessage message, long timestamp) {
        int status = message.getStatus();
        int note = message.getData1();
        int velocity = message.getData2();


        if (status == ShortMessage.NOTE_ON || status == ShortMessage.CONTROL_CHANGE) {

            for(LaunchpadListener listener : launchPad.getLaunchpadListeners().values()){

                if(velocity > 0) {
                    listener.onButtonPushed(LaunchPadPro.getButton(note), timestamp);
                }else{
                    listener.onButtonReleased(LaunchPadPro.getButton(note), timestamp);
                }
            }
        }
    }

    public LaunchPadPro getOwningLaunchpad(){
        return launchPad;
    }

    @Override
    public void close() {}
}
