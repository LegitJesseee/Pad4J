package me.jessepayne.pad4j.core.enums;

public enum CCButtonEnum {

     SCENE_1(89),
     SCENE_2(79),
     SCENE_3(69),
     SCENE_4(59),
     SCENE_5(49),
     SCENE_6(39),
     SCENE_7(29),
     SCENE_8(19),
     UP_ARROW(91),
     DOWN_ARROW(92),
     LEFT_ARROW(93),
     RIGHT_ARROW(94),
     SESSION(95),
     NOTE(96),
     DEVICE(97),
     USER(98),
     SHIFT(80),
     CLICK(70),
     UNDO(60),
     DELETE(50),
     QUANTISE(40),
     DUPLICATE(30),
     DOUBLE(20),
     RECORD(10),
     RECORD_ARM(1),
     TRACK_SELECT(2),
     MUTE(3),
     SOLO(4),
     VOLUME(5),
     PAN(6),
     SENDS(7),
     STOP_CLIP(8);

    int note;

    CCButtonEnum(int note){
        this.note = note;
    }

    public int getNote(){
        return note;
    }

}
