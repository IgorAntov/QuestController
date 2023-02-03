package org.autoquest.service;

import org.autoquest.connections.Params;

public class Global {

    public static boolean START;
    public static boolean ABORT;
    public static boolean RESET;

    public static int currentStep;

    public static void increaseStepNumber() {
        currentStep++;
        Params.STEPNUMBER.setValue(currentStep);
    }

    public static void resetStepNumber() {
        currentStep = 0;
        Params.STEPNUMBER.setValue(currentStep);
    }

}
