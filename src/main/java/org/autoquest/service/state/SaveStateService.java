package org.autoquest.service.state;

import org.autoquest.quest.StateStore;

import java.io.File;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SaveStateService {

    int statCode = 1;
    public SaveStateService() {
        long t = 5_000;
        URL u = SaveStateService.class.getResource("/qstate.xml");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() ->
        {
            if (u != null) {
//                WriteXML.writeParametersToXML();
                if (statCode == 1) {
                    ReadXML.readParametersFromXML(StateStore.getParameterStore());
                }
                if (statCode != StateStore.getStateCode()) {
                    WriteXML.writeParametersToXML();
                    statCode = StateStore.getStateCode();
                }
            } else {
                WriteXML.writeParametersToXML();
            }
        }, t, t, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(exec::shutdown));
    }
}
