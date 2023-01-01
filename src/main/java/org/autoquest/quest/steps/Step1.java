package org.autoquest.quest.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.connections.Params;
import org.autoquest.quest.Action;
import org.autoquest.quest.StepTemplate;
import org.autoquest.quest.Transition;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step1 extends StepTemplate {

    public static void init() {
        step.setStepName("Шаг 1");
        Action action1 = new Action("Action1");
        action1.setDesc("Подробное описание действия 1");

        MBParameter ACTION1 = new MBParameter("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action1.defineAction(() -> ACTION1.setValue(true));

//        action1.setEnabled(Params.ACTION1_ENABLED);
//        action1.setEnabledConfirm(Params.ACTION1_ENABLED_CFM);

        Action action2 = new Action("Action2");
        action2.setDesc("Подробное описание действия 2");

        MBParameter ACTION2 = new MBParameter("ACTION2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        action2.defineAction(() -> ACTION2.setValue(true));

//        action2.setEnabled(Params.ACTION2_ENABLED);
//        action2.setEnabledConfirm(Params.ACTION2_ENABLED_CFM);

        Transition transition1 = new Transition("Transition1");
        transition1.condition(Params.KEY1::getBoolValue);
        transition1.setBypassParam(Params.BYPASS_KEY1);

        step.setNextStep(Step2.getPointer());
        step.addAction(action1);
        step.addAction(action2);
        step.addTransition(transition1);
    }
}
