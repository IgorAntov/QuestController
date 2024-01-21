package org.autoquest.connections.adapters;

import org.autoquest.connections.DataType;
import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class Router {

    private static final ArrayList<AdapterParameter> adapterParameters = new ArrayList<>();

    public static void routerStart() {
        new RunAdapter().watch();
    }

    public static void setRoute(MBParameter in, MBParameter out) {
        adapterParameters.add(new AdapterParameter(in, out));
    }

    private static class RunAdapter extends Thread {

        @Override
        public void run() {
            try {
                do {
                    for (AdapterParameter ab : adapterParameters) {
                        if (ab.getIn().getDataType().equals(ab.getOut().getDataType())) {
                            if (ab.getIn().getDataType().equals(DataType.BOOL)) {
                                ab.getOut().setValue(ab.getIn().getBoolValue());
                            }
                            if (ab.getIn().getDataType().equals(DataType.INT32)) {
                                ab.getOut().setValue(ab.getIn().getInt32Value());
                            }
                            if (ab.getIn().getDataType().equals(DataType.FLOAT)) {
                                ab.getOut().setValue(ab.getIn().getFloatValue());
                            }
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
