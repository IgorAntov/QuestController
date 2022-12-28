package org.autoquest.quest.steps;

import org.autoquest.connections.Params;
import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.quest.StepTemplate;
import org.autoquest.quest.Transition;

public class Step1 extends StepTemplate {

    public static void init() {
        step.setStepName("Шаг 1");

        Action action1 = new Action("Action1");
        action1.setStatusParam(Params.ACTION1);
        action1.defineAction(() -> Params.ACTION1.setValue(true));
        action1.setTestParams(Params.ACTION1_TEST_START, Params.ACTION1_TEST_STOP);

        Transition transition1 = new Transition("Transaction1");
        transition1.condition(Params.KEY1::getValue);
        transition1.setBypassParam(Params.BYPASS_KEY1);

        step.setNextStep(Step2.getPointer());
        step.addAction(action1);
        step.addTransition(transition1);
    }
}
