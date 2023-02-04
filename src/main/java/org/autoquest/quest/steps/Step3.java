package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step3 extends Step {
    private static final Step3 step3 = new Step3();

    public static Step3 getInstance() {
        return step3;
    }

    private Step3() {
        super("Выкл свет");
        setContinuous();
        setRunOnInit();
        Action action1 = new Action("Action1S3");
        action1.setDesc("Действия 1 S3");

        MBParameter ACTION1 = new MBParameter("ACTION1S3", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> {
            ACTION1.setValue(true);
            System.out.println("Step 3 Action 1 Done");
//            StoredActions.getAction("Action1").stopAction();
        });

        MBParameter KEY1 = new MBParameter("KEY1S3", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

        Transition transition1 = new Transition("Transition1S3");
        transition1.setDesc("Переход 1 S3");
        transition1.condition(KEY1::getBoolValue);

        addAction(action1);
        addTransition(transition1);
    }
}
