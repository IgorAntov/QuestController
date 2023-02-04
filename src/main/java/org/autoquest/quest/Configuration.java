package org.autoquest.quest;

import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.steps.Step3;
import org.autoquest.quest.steps.Step4;
import org.autoquest.quest.timealarm.secX1.Timer1;
import org.autoquest.quest.timealarm.secX1.TimerAction1;

public class Configuration {

    public static void inti() {
        Step1.getInstance();
        Step2.getInstance();
        Step3.getInstance();
        Step4.getInstance();

        Step3.getInstance().setNextStep(Step4.getInstance());
        Step4.getInstance().setNextStep(Step3.getInstance());

        TimerAction1.getInstance();
        Timer1.getInstance();
    }
}
