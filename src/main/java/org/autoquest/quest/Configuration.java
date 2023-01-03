package org.autoquest.quest;

import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.steps.Step3;

public class Configuration {

    public static void inti() {
        Step1.getInstance();
        Step2.getInstance();
        Step3.getInstance();
    }
}
