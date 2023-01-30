package org.autoquest.quest.view;

import org.autoquest.connections.Params;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;

import java.util.ArrayList;

public class TimerLabel {

    public ArrayList<IGraphic> getTimerLabel(int x, int y) {
        ArrayList<IGraphic> timerElementContainer = new ArrayList<>();
        //Hours
        DynamicText hours = new DynamicText();
        hours.setHint("Timer");
        hours.setDesc("Timer");
        hours.setPosition(x, y);
        hours.setParameter(Params.TIME_HOUR);
        timerElementContainer.add(hours);
        //Colon
        StaticText colon1 = new StaticText();
        colon1.setHint("Timer");
        colon1.setDesc(":");
        colon1.setPosition(x + 20, y - 5);
        timerElementContainer.add(colon1);
        //Minutes
        DynamicText mins = new DynamicText();
        mins.setHint("Timer");
        mins.setDesc(":");
        mins.setPosition(x + 30, y);
        mins.setParameter(Params.TIME_MIN);
        timerElementContainer.add(mins);
        //Colon
        StaticText colon2 = new StaticText();
        colon2.setHint("Timer");
        colon2.setDesc(":");
        colon2.setPosition(x + 50, y - 5);
        timerElementContainer.add(colon2);
        //Seconds
        DynamicText secs = new DynamicText();
        secs.setHint("Timer");
        secs.setDesc("Timer");
        secs.setPosition(x + 60, y);
        secs.setParameter(Params.TIME_SEC);
        timerElementContainer.add(secs);

        return timerElementContainer;
    }
}
