package org.autoquest.connections.adapters;

import org.autoquest.connections.IParameterCoil;

import java.util.ArrayList;

public class Adapter {

     private static final ArrayList<AdapterBoolean> adapterBooleans = new ArrayList<>();

    public Adapter() {
        new RunAdapter().watch();
    }

    public static void setAdapterBoolean(IParameterCoil in, IParameterCoil out) {
         adapterBooleans.add(new AdapterBoolean(in, out));
     }

     private static class RunAdapter extends Thread {

         @Override
         public void run() {
             for (AdapterBoolean ab: adapterBooleans) {
                 ab.getOut().setValue(ab.getIn().getValue());
             }
             try {
                 sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }

         public void watch() {
             start();
         }
     }
}
