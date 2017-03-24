package me.jessepayne.pad4j.core.enums;

public enum LiveLayout {

    SESSION((byte) 0x0),
    DRUM((byte) 0x1),
    CHROMATIC_NOTE((byte) 0x2),
    USER_DRUM((byte) 0x3),
    AUDIO_BLANK((byte) 0x4),
    FADER((byte) 0x5),
    RECORD_ARM_SESSION((byte) 0x6),
    TRACK_SELECT_SESSION((byte) 0x7),
    MUTE_SESSION((byte) 0x8),
    SOLO_SESSION((byte) 0x9),
    VOLUME_FADER((byte) 0x0A);

    private byte data;

    LiveLayout(byte data){
        this.data = data;
    }

    public byte getData(){
        return data;
    }



}
