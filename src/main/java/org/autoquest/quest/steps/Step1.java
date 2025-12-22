package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.*;
import org.autoquest.service.video.VideoPlayer;

import static org.autoquest.connections.adapters.SimulatorParams.UNOR3_1_PF1;
import static org.autoquest.connections.adapters.SimulatorParams.UNOR3_1_PF2;
import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step1 extends Step {
    private static final Step1 step1 = new Step1();
    public static MBParameter ACTION1;

    public static Step1 getInstance() {
        return step1;
    }

    private Step1() {
        super("Шаг 1");
        Action action1 = new Action("Включаем лампу", ActionType.STORED);
        action1.setDesc("Действия 1. Подоробное описание действия. Некоторые замечания");

        ACTION1 = new MBParameter("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
 //       action1.defineAction(() -> ACTION1.setValue(true));
       action1.defineAction(() -> UNOR3_1_PF2.setValue(true));

        Action action2 = new Action("Action2S1");
        action2.setDesc("Действие 2 название");

        MBParameter ACTION2 = new MBParameter("ACTION2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
//        action2.defineAction(() -> ACTION2.setValue(true));
        action2.defineAction(() -> UNOR3_1_PF2.setValue(true));

        //        action2.defineAction(() -> Player.playSound("sound1.mp3", MixerDeviceStore.DEVICES[1]));

        Action action3 = new Action("Action3S1");
        action3.setDesc("Video1Play");
//        action3.setDelay(10);
        action3.defineAction(() -> {
            System.out.println("play V");
            VideoPlayer.play("test.mp4");
//            action3.setDelay(3);
           System.out.println("pause V");
            VideoPlayer.pauseClip("test.mp4");

            System.out.println("skip V");
//           VideoPlayer.skip("test.mp4", 5000);
//            System.out.println("pause V");
//            VideoPlayer.pauseClip("test.mp4");
            System.out.println("Step1 !!!!");
        });

        Action action4 = new Action("Action4S1");
        action4.setDesc("Video1Stop");
        action4.setDelay(5);
//        action4.defineAction(() -> VideoPlayer.stop("sound1.mp3"));
//        action4.defineAction(() -> VideoPlayer.resume("test.mp4"));
//        action4.setDelay(5);
 //       action4.defineAction(() -> {
//            VideoPlayer.stop("test.mp4");

 //       });

        MBParameter KEY1 = new MBParameter("KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1S1");
        transition1.setDesc("Переход 1 \n Переход 1");
        transition1.setBypassButtonXY(95, 495);
        transition1.condition(KEY1::getBoolValue);

        MBParameter KEY2 = new MBParameter("KEY2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition2 = new Transition("Transition2S1");
        transition2.setDesc("Переход 2 \n Переход 2");
        transition2.setBypassButtonXY(715, 280);
        transition2.condition(KEY2::getBoolValue);

        Transition transition3 = new Transition("Transition3S1");
        transition3.setDesc("Переход 3 без ключа");
        transition3.setWithoutKeys(true);
        transition3.condition(() -> true);

        setNextStep(Step2.getInstance());
        addAction(action1);
        addAction(action2);
        addAction(action3);
        addAction(action4);

        addTransition(transition1);
        addTransition(transition2);
        addTransition(transition3);
    }

}
