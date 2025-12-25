package org.autoquest.quest.questConfig.steps;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.quest.Action;
import org.autoquest.quest.ActionType;
import org.autoquest.quest.Step;
import org.autoquest.quest.Transition;
import org.autoquest.service.video.VideoPlayer;

import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.quest.questConfig.params.SimulatorParams.UNOR3_1_PF2;

public class Step5 extends Step {
    private static final Step5 step5 = new Step5();
    public static MBParameter ACTION1;

    public static Step5 getInstance() {
        return step5;
    }

    private Step5() {
        super("Шаг 3");
        setFinalStep(true);

        Action action1 = new Action("ActionA1S3 XXXXXXX XXXXX", ActionType.STORED);
        action1.setActionNick("Действие 1 шага 3");
        action1.setDesc("Действия 1. Подоробное описание действия. Некоторые замечания");
        action1.defineAction(() -> {boolean x = true;});

        Action action2 = new Action("Action2S3");
        action2.setActionNick("Действие 2 шага 3");
        action2.setDesc("Действие 2 название описание");
        action2.defineAction(()-> {boolean x = true;});

        Transition transition1 = new Transition("Transition1S3");
        transition1.setTransitionNick("Переход 1 Шага 3");
        transition1.setDesc("Переход 1 \n Переход 1 описание");
        transition1.condition(()->false);

        Transition transition2 = new Transition("Transition2S3");
        transition2.setTransitionNick("Переход 2 Шага 3 ");
        transition2.setDesc("Переход 2 \n Переход 2 описание");
        transition2.condition(()->false);

        addAction(action1);
        addAction(action2);
        addTransition(transition1);
        addTransition(transition2);
    }

}
