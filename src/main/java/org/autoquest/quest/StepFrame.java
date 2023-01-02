package org.autoquest.quest;

import org.autoquest.quest.view.*;

import java.util.ArrayList;

public class StepFrame {

    protected final Step step;

    public StepFrame(Step step) {
        this.step = step;
    }

    public void start() {
        step.start();
    }

    public ArrayList<IGraphic> getStepFrame(int x, int y) {
        int topSetOff = 10;
        int lineLeft = 15;
        int vSpacing = 33;
        ArrayList<IGraphic> frameCollector = new ArrayList<>();

        StaticTextFrame staticTextFrame = new StaticTextFrame();
        staticTextFrame.setPosition(x, y);
        staticTextFrame.setDesc(step.getStepName());
        int height = (step.getActions().size() + step.getTransitions().size()) * vSpacing + topSetOff + 40;
        staticTextFrame.setSize(height, 310);
        frameCollector.add(staticTextFrame);
        int spacingIndex = 1;
        for (Action action : step.getActions()) {

            ButtonTest buttonTest = new ButtonTest();
            buttonTest.setPosition(staticTextFrame.getX() + lineLeft, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
            buttonTest.setParameterControl(action.getTestStart());
            frameCollector.add(buttonTest);

            if (action.getTestStop() != null) {
                ButtonTest buttonStopTest = new ButtonTest();
                buttonStopTest.setName("Стоп");
                buttonStopTest.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() + 5, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
                buttonStopTest.setParameterControl(action.getTestStop());
                frameCollector.add(buttonStopTest);
            }

            Switch switch1 = new Switch();
            switch1.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() * 2 + 15, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex + 5);
            switch1.setParameterControl(action.getEnabled());
            switch1.setParameterStatus(action.getEnabledConfirm());
            switch1.setHint("Вкл/Выкл");
            frameCollector.add(switch1);

            Led led1 = new Led();
            led1.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() * 2 + switch1.getWidth() + 22, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex + 5);
            led1.setParameter(action.getStatusParam());
            led1.setHint(action.getDesc());
            frameCollector.add(led1);

            StaticText staticText = new StaticText();
            staticText.setPosition(staticTextFrame.getX() + lineLeft + buttonTest.getWidth() * 2 + switch1.getWidth() + led1.getWidth() + 28, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex);
            staticText.setDesc(action.getActionName());
            frameCollector.add(staticText);
            spacingIndex++;
        }

        for (Transition transition : step.getTransitions()) {
            Switch switch1 = new Switch();
            switch1.setPosition(staticTextFrame.getX() + lineLeft, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex + 10);
            switch1.setParameterControl(transition.getBypass());
            switch1.setParameterStatus(transition.getBypassCFM());
            switch1.setHint("Вкл/Выкл");
            frameCollector.add(switch1);

            StaticText staticText = new StaticText();
            staticText.setPosition(staticTextFrame.getX() + lineLeft + switch1.getWidth() + 10, staticTextFrame.getY() + topSetOff + vSpacing * spacingIndex + 5);
            staticText.setDesc(transition.getDesc());
            frameCollector.add(staticText);

            spacingIndex++;
        }

        return frameCollector;
    }
}
