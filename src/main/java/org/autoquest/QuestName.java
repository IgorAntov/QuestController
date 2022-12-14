package org.autoquest;

import org.autoquest.connections.Params;
import org.autoquest.connections.units.MBUnitList;
import org.autoquest.service.CommandLineP;

import java.net.UnknownHostException;

public class QuestName {


    public static void main(String[] args) {
        try {
            MBUnitList.init();
            Params.init();
            CommandLineP.parseCL(args);
            MBUnitList.runListener();

  //          Params.paramB1.setValue(true);
  //          Params.paramB2.setValue(true);

 //           Params.paramI1.setValue(10);
 //           Params.paramI2.setValue(20);
 //           Params.paramI3.setValue(30);
 //           Params.paramI4.setValue(40);

  //          Params.paramF1.setValue(-654.3541f);
  //          Params.paramF2.setValue(1326546.1f);


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}