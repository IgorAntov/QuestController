package org.autoquest.quest;

import org.autoquest.quest.view.ButtonBypass;
import org.autoquest.quest.view.IGraphic;

import java.util.ArrayList;

public class StepBypassButtons {

    protected final Step step;

    public StepBypassButtons(Step step) {
        this.step = step;
    }

    public ArrayList<IGraphic> getButtons() {
        ArrayList<IGraphic> bypassButtonCollector = new ArrayList<>();
        System.out.println("Tr: " + step.getTransitions());
        for (Transition transition : step.getTransitions()) {
            ButtonBypass buttonBypass = new ButtonBypass();
            buttonBypass.setHint(transition.getDesc());
            buttonBypass.setDesc(transition.getName());
            buttonBypass.setPosition(transition.getBypassButtonX(), transition.getBypassButtonY());
            buttonBypass.setParameterStatus(step.getStatusActive());
            buttonBypass.setParameterControl(transition.getBypass());
            bypassButtonCollector.add(buttonBypass);
        }
        return bypassButtonCollector;
    }

}
