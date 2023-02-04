package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.*;
import org.autoquest.quest.view.IGraphic;
import org.autoquest.service.SoundPlayer.MixerDeviceStore;
import org.autoquest.service.SoundPlayer.Player;

import java.util.ArrayList;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step2 extends Step {
    private static final Step2 step2 = new Step2();
    public static Step2 getInstance() {
        return step2;
    }
    private Step2() {
        super("Шаг 2");
        Action action1 = new Action("Action1S2");
        action1.setDesc("Действия 1");

        MBParameter ACTION1 = new MBParameter("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> ACTION1.setValue(true));

        Action action2 = new Action("Action2S2");
        action2.setDesc("Действие 2 название");

        MBParameter ACTION2 = new MBParameter("ACTION2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action2.defineAction(() -> ACTION2.setValue(true));
//        action2.defineAction(() -> Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[1], true, 100));

        MBParameter KEY1 = new MBParameter("KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1S2");
        transition1.setDesc("Переход 1");
        transition1.setBypassButtonXY(690, 555);
        transition1.condition(KEY1::getBoolValue);

        MBParameter KEY2 = new MBParameter("KEY2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition2 = new Transition("Transition2S2");
        transition2.setDesc("Переход 2");
        transition2.setBypassButtonXY(490, 475);
        transition2.condition(KEY2::getBoolValue);

        addAction(action1);
        addAction(action2);
        addTransition(transition1);
        addTransition(transition2);
    }

}
