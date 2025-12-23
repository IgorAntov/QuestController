package org.autoquest.quest.questConfig.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;

import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step4 extends Step {

    private static final Step4 step4 = new Step4();

    public static Step4 getInstance() {
        return step4;
    }

    private Step4() {
        super("Вкл свет");
        setContinuous();
        setNextStep(Step3.getInstance());
        Action action1 = new Action("Action1S4");
        action1.setDesc("Действия 1 S4");

        MBParameter ACTION1 = new MBParameter("ACTION1 S4", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> {
            ACTION1.setValue(true);
            System.out.println("Step 4 Action 1 Done");
//            StoredActions.getAction("Action1S4").stopAction();
        });

        MBParameter KEY1 = new MBParameter("KEY1S4", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1S4");
        transition1.setDesc("Переход 1 S4");
        transition1.condition(KEY1::getBoolValue);

        addAction(action1);
        addTransition(transition1);
    }
}
