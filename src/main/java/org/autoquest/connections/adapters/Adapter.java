package org.autoquest.connections.adapters;

import org.autoquest.connections.IParameterCoil;
import org.autoquest.connections.Params;

import java.util.ArrayList;

public class Adapter {

    private static final ArrayList<AdapterBoolean> adapterBooleans = new ArrayList<>();

    public static void adapterStart() {
        new RunAdapter().watch();
    }

    public static void setAdapterBoolean(IParameterCoil in, IParameterCoil out) {
        adapterBooleans.add(new AdapterBoolean(in, out));
    }

    private static class RunAdapter extends Thread {

        @Override
        public void run() {
            try {
                do {
                    for (AdapterBoolean ab : adapterBooleans) {
                        ab.getOut().setValue(ab.getIn().getValue());
                        System.out.println("In: " + ab.getIn().getValue());
                        System.out.println("Out: " + ab.getOut().getValue());
                    }
                    sleep(1000);
                } while (true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void watch() {
            start();
        }
    }
}
