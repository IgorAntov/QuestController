package org.autoquest.connections.adapters;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class Adapter {

    private static final ArrayList<AdapterParameter> adapterParameters = new ArrayList<>();

    public static void adapterStart() {
        new RunAdapter().watch();
    }

    public static void setAdapter(MBParameter in, MBParameter out) {
        adapterParameters.add(new AdapterParameter(in, out));
    }

    private static class RunAdapter extends Thread {

        @Override
        public void run() {
            try {
                do {
                    for (AdapterParameter ab : adapterParameters) {
                        if (ab.getIn().getDataType().equals(ab.getOut().getDataType())) {
                            ab.getOut().setValue(ab.getIn().getBoolValue());
                        } else throw new IllegalArgumentException();
                    }
                    sleep(100);
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
