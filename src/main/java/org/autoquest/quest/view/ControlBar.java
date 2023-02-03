package org.autoquest.quest.view;

import org.autoquest.connections.Params;

import java.util.ArrayList;

public class ControlBar {
    private final String questName;
    private final String iconName;

    public ControlBar(String questName, String iconName) {
        this.questName = questName;
        this.iconName = iconName;
    }

    public ArrayList<IGraphic> getElements(int x, int y) {
        ArrayList<IGraphic> elements = new ArrayList<>();
        StatisImage questIcon = new StatisImage("qt_icon.svg");
        questIcon.setImageSize(40, 40);
        questIcon.setPosition(x, y);
        elements.add(questIcon);

        StaticText nameLabel = new StaticText();
        nameLabel.setFontSize(18);
        nameLabel.setDesc("Name");
        nameLabel.setPosition(x + 50, y + 10);
        elements.add(nameLabel);

        ButtonTest startButton = new ButtonTest();
        startButton.setName("Старт");
        startButton.setDesc("SB_Desc");
        startButton.setHint("Start");
        startButton.setSize(25, 70);
        startButton.setParameterControl(Params.START);
        startButton.setParameterStatus(Params.START_FB);
        startButton.setPosition(x + 5, y + 60);
        elements.add(startButton);

        ButtonTest stopButton = new ButtonTest();
        stopButton.setName("Стоп");
        stopButton.setDesc("Abort_Desc");
        stopButton.setHint("Stop");
        stopButton.setSize(25, 70);
        stopButton.setParameterControl(Params.ABORT);
        stopButton.setParameterStatus(Params.ABORT_FB);
        stopButton.setPosition(x + 5, y + 60);
        elements.add(stopButton);

        ButtonTest pauseButton = new ButtonTest();
        pauseButton.setName("Пауза");
        pauseButton.setDesc("Pause_Desc");
        pauseButton.setHint("pause");
        pauseButton.setSize(25, 70);
        pauseButton.setParameterControl(Params.PAUSE);
        pauseButton.setParameterStatus(Params.PAUSE_FB);
        pauseButton.setPosition(x + 80, y + 60);
        elements.add(pauseButton);

        ButtonTest rerunButton = new ButtonTest();
        rerunButton.setName("Продол.");
        rerunButton.setDesc("Rerun_Desc");
        rerunButton.setHint("rerun");
        rerunButton.setSize(25, 70);
        rerunButton.setParameterControl(Params.RERUN);
        rerunButton.setParameterStatus(Params.RERUN_FB);
        rerunButton.setPosition(x + 80, y + 60 );
        elements.add(rerunButton);

        StaticText stepLabel = new StaticText();
        stepLabel.setFontSize(18);
        stepLabel.setDesc("ШАГ: ");
        stepLabel.setPosition(x + 50, y + 140);
        elements.add(stepLabel);

        DynamicText stepNumber = new DynamicText();
        stepNumber.setHint("StepNumber");
        stepNumber.setDesc("StepNumber");
        stepNumber.setPosition(x + 100, y + 143); //1120 160
        stepNumber.setParameter(Params.STEPNUMBER);
        elements.add(stepNumber);
        
        return elements;
    }
}
