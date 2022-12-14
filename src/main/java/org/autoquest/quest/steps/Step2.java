package org.autoquest.quest.steps;

import org.autoquest.connections.Params;
import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.quest.StepTemplate;
import org.autoquest.quest.Transition;

public class Step2 extends StepTemplate {

    public Step2() {
        Action action1 = new Action();
        action1.defineAction(() -> Params.ACTION1.setValue(true));
        Transition transition1 = new Transition(step);
        transition1.condition(() -> Params.KEY_1.getValue() && Params.KEY_2.getValue());
        transition1.setStep(Step3.getPointer());
        step.addAction(action1);
        step.addTransition(transition1);
    }

}
