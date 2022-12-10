package org.autoquest.quest;

import java.util.ArrayList;
import java.util.Iterator;

public class StepListToExecute {

    private final ArrayList<Step> stepsStack = new ArrayList<>();

    void run() {
        while (true) {
            Iterator iterator = stepsStack.iterator();
            while (iterator.hasNext()) {
  //              iterator.next().execute();
            }
        }
    }

}
