package org.autoquest.quest.questConfig.steps;

import org.autoquest.quest.questConfig.timealarm.secX1.Timer1;
import org.autoquest.quest.questConfig.timealarm.secX1.TimerAction1;

public class Configuration {

    public static void inti() {
        Step1.getInstance();
        Step2.getInstance();
        Step3.getInstance();
        Step4.getInstance();

        // Continuous steps
        Step3.getInstance().setNextStep(Step4.getInstance());
        Step4.getInstance().setNextStep(Step3.getInstance());

        // Timers
        TimerAction1.getInstance();
        Timer1.getInstance();
    }
}
