package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.quest.view.*;

import java.util.ArrayList;

public class StepTemplate {

    protected static final Step step = new Step();

    public StepTemplate() {
    }

    public static void start() {
        step.start();
    }
    public static Step getPointer() {
        return step;
    }

    public static ArrayList<IGraphic> getStepFrame(int x, int y) {
        int topSetOff = 10;
        int lineLeft = 15;
        int vSpacing = 33;
        ArrayList<IGraphic> frameCollector = new ArrayList<>();

        StaticTextFrame staticTextFrame = new StaticTextFrame();
        staticTextFrame.setPosition(x, y);
        staticTextFrame.setDesc(step.getStepName());
        int height =  (step.getActions().size() + step.getTransitions().size()) * vSpacing + topSetOff + 20;
        staticTextFrame.setSize(height, 300);
        frameCollector.add(staticTextFrame);

        int spacingIndex = 1;
        for (Action action: step.getActions()) {

            ButtonTest buttonTest = new ButtonTest();
            buttonTest.setPosition(staticTextFrame.getX() + lineLeft, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
            buttonTest.setParameterControl(action.getTestStart());
            buttonTest.setParameterStatus(action.getStatusParam());
            frameCollector.add(buttonTest);

            if (action.getTestStop() != null) {
                ButtonTest buttonStopTest = new ButtonTest();
                buttonStopTest.setName("Стоп");
                buttonStopTest.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() + 5, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
                buttonStopTest.setParameterControl(action.getTestStop());
                buttonStopTest.setParameterStatus(action.getStatusParam());
                frameCollector.add(buttonStopTest);
            }

            Led led1 = new Led();
            led1.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() * 2 + 20, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex + 5);
            led1.setParameter(action.getStatusParam());
            led1.setHint(action.getDesc());
            frameCollector.add(led1);

            StaticText staticText = new StaticText();
            staticText.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() * 2 + led1.getWidth() + 28, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
            staticText.setDesc(action.getActionName());
            frameCollector.add(staticText);
            spacingIndex++;
        }
        return frameCollector;
    }
}
