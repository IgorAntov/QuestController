package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.quest.StoredActions;
import org.autoquest.quest.Transition;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step3 extends Step {
    private static final Step3 step3 = new Step3();

    public static Step3 getInstance() {
        return step3;
    }

    private Step3() {
        setStepName("Шаг 3");
//        setContinuous();
//        setRunOnInit();
//        setNextStep(xxx);
        Action action1 = new Action("Action1");
        action1.setDesc("Действия 1");

        MBParameter ACTION1 = new MBParameter("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> {
            ACTION1.setValue(true);
            StoredActions.getAction("Action1").stopAction();
        });

        MBParameter KEY1 = new MBParameter("KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1");
        transition1.setDesc("Переход 1");
        transition1.setBypassButtonXY(150, 50);
        transition1.condition(KEY1::getBoolValue);

        addAction(action1);
        addTransition(transition1);
    }
}
