package org.autoquest.quest.view;

import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;
import org.autoquest.quest.view.ButtonBypass;
import org.autoquest.quest.view.IGraphic;

import java.util.ArrayList;

public class StepBypassButtons {

    protected final Step step;
   // private ArrayList<IGraphic> bypassButtonCollector_ = new ArrayList<>();

    public StepBypassButtons(Step step) {
        this.step = step;
   //     bypassButtonCollector_ = getButtons();
    }

    public ArrayList<IGraphic> getButtons() {
        ArrayList<IGraphic> bypassButtonCollector = new ArrayList<>();
        for (Transition transition : step.getTransitions()) {
            if (!transition.isWithoutKeys()) {
                ButtonBypass buttonBypass = new ButtonBypass();
                buttonBypass.setHint(transition.getDesc());
                buttonBypass.setDesc(transition.getName());
                buttonBypass.setPosition(transition.getBypassButtonX(), transition.getBypassButtonY());
                buttonBypass.setParameterStatus(transition.getBypassVisible());
            //    buttonBypass.setParameterControl(transition.getBypass());
             //       buttonBypass.setParameterStatus(transition.getEnabledConfirm());
                    buttonBypass.setParameterControl(transition.getEnabled());

                bypassButtonCollector.add(buttonBypass);
            }
        }
        return bypassButtonCollector;
    }

//    public void setPositionOnScreenForTranBypass(String transitionName, int X, int Y) {
//        for (IGraphic b : bypassButtonCollector_) {
//            if (b.getName().equals(transitionName)) {

//                b. (transition.getBypassButtonX(), transition.getBypassButtonY());
 //           }
 //       }
 //   }
}
