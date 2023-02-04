package org.autoquest.quest.timealarm.secX1;

import org.autoquest.quest.QuestTimer;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;
import org.autoquest.service.Global;

public class Timer1 extends Step {

    private static final Timer1 timer1 = new Timer1();

    public static Timer1 getInstance() {
        return timer1;
    }

    private Timer1() {
        super("TimeAlarm");
        setContinuous();
        setRunOnInit();

        Transition transition1 = new Transition("Transition1S3");
        transition1.setDesc("Шаг 1 и время больше 10 сек");
        transition1.condition(() -> {
            System.out.println(QuestTimer.getTime());
            System.out.println(Global.currentStep);
            return Global.currentStep == 1 && (QuestTimer.getTime() > 10);
                }
        );
        addTransition(transition1);
        setNextStep(TimerAction1.getInstance());
    }
}
