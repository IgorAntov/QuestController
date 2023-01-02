package org.autoquest.quest;

import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;

public class Configuration {

    public static void inti() {
        Step1.getInstance();
        Step2.getInstance();
    }
}
