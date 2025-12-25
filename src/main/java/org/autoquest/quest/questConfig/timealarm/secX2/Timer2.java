package org.autoquest.quest.questConfig.timealarm.secX2;

import org.autoquest.quest.QuestTimer;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;
import org.autoquest.quest.questConfig.steps.Step2;
import org.autoquest.quest.questConfig.timealarm.secX1.TimerAction1;
import org.autoquest.service.Global;

public class Timer2 extends Step {

    private static final Timer2 timer1 = new Timer2();

    public static Timer2 getInstance() {
        return timer1;
    }

    private Timer2() {
        super("TimeAlarm 2");
        setContinuous();
        setRunOnInit();

        Transition transition1 = new Transition("Timer2");
        transition1.setTransitionNick("Шаг 2 и время больше 20 сек");
        transition1.setDesc("Шаг 2 и время больше 10 сек описание");
        transition1.condition(() -> {
            System.out.println(QuestTimer.getTime());
            System.out.println(Step2.getInstance().getStartTime());
            return Global.currentStep == 2 && ((QuestTimer.getTime() - Step2.getInstance().getStartTime()) > 20 && Step2.getInstance().getStatusActive().getBoolValue());
                }
        );
        addTransition(transition1);
        setNextStep(TimerAction2.getInstance());
    }
}
