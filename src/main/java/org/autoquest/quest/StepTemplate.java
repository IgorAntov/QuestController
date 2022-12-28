package org.autoquest.quest;

import org.autoquest.quest.steps.StepLast;
import org.autoquest.quest.view.ButtonTest;
import org.autoquest.quest.view.IGraphic;
import org.autoquest.quest.view.StaticTextFrame;

import java.util.ArrayList;

public class StepTemplate {

    protected static final Step step = new Step();

    public StepTemplate() {
        step.setNextStep(new StepLast());
    }

    public static void start() {
        step.start();
    }

    public static Step getPointer() {
        return step;
    }

    public static ArrayList<IGraphic> getStepFrame(int x, int y) {
        int lineLeft = 10;
        int vSpacing = 20;
        ArrayList<IGraphic> frameCollector = new ArrayList<>();

        StaticTextFrame staticTextFrame = new StaticTextFrame();
        staticTextFrame.setPosition(x, y);
        staticTextFrame.setDesc(step.getStepName());
        int height =  (step.getActions().size() + step.getTransitions().size()) * vSpacing;
        staticTextFrame.setSize(height, 250);
        frameCollector.add(staticTextFrame);

        int spacingIndex = 1;
        for (Action action: step.getActions()) {

            ButtonTest buttonTest = new ButtonTest();
            buttonTest.setPosition(staticTextFrame.getX() + lineLeft, staticTextFrame.getX() + vSpacing);
            buttonTest.setParameterControl(action.getTestStart());
            buttonTest.setParameterStatus(action.getStatusParam());
            frameCollector.add(buttonTest);

            if (action.getTestStop() != null) {
                ButtonTest buttonStopTest = new ButtonTest();
                buttonStopTest.setName("Стоп");
                buttonStopTest.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() + 10, staticTextFrame.getX() + vSpacing);
                buttonStopTest.setParameterControl(action.getTestStop());
                buttonStopTest.setParameterStatus(action.getStatusParam());
                frameCollector.add(buttonStopTest);
            }
        }
        return frameCollector;
    }
}
