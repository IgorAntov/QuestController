package org.autoquest;

import org.autoquest.connections.Params;
import org.autoquest.connections.units.MBUnitList;
import org.autoquest.connections.units.units.WSMBUnit;

import java.net.UnknownHostException;

public class QuestName {


    public static void main(String[] args) {
        try {
            MBUnitList.init();
            Params.init();
            WSMBUnit.getInstance().evalMapSize();
            WSMBUnit.getInstance().startListen();

            Params.paramB1.setValue(false);
            Params.paramB2.setValue(true);

            Params.paramI1.setValue(10);
            Params.paramI2.setValue(20);
            Params.paramI3.setValue(30);
            Params.paramI4.setValue(40);

            for (int i = 0; i < 100; i++) {
                Params.paramI5.setValue(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}