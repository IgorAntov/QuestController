package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.*;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step1 extends Step {
    private static final Step1 step1 = new Step1();

    public static Step1 getInstance() {
        return step1;
    }

    private Step1() {
        super("Шаг 1");
        Action action1 = new Action("Action1S1", ActionType.STORED);
        action1.setDesc("Действия 1");

        MBParameter ACTION1 = new MBParameter("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> ACTION1.setValue(true));

        Action action2 = new Action("Action2S1");
        action2.setDesc("Действие 2 название");

        MBParameter ACTION2 = new MBParameter("ACTION2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action2.defineAction(() -> ACTION2.setValue(true));

        MBParameter KEY1 = new MBParameter("KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1S1");
        transition1.setDesc("Переход 1");
        transition1.setBypassButtonXY(95, 495);
        transition1.condition(KEY1::getBoolValue);

        MBParameter KEY2 = new MBParameter("KEY2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition2 = new Transition("Transition2S1");
        transition2.setDesc("Переход 2");
        transition2.setBypassButtonXY(715, 280);
        transition2.condition(KEY2::getBoolValue);

        setNextStep(Step2.getInstance());
        addAction(action1);
        addAction(action2);
        addTransition(transition1);
        addTransition(transition2);
    }

}
