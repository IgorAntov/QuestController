package org.autoquest.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

//import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;

public class CreateXMLForRapidScada {

    private final StringBuilder sb = new StringBuilder();
    public CreateXMLForRapidScada() {
    }

    public void createXMLFile(String path, String filename) {

        XMLModBusTCPBuilder xmlModBusTCPBuilder = new XMLModBusTCPBuilder(WS_MB_UNIT_SLAVE);
        String result = xmlModBusTCPBuilder.buildXMLContent();

        try {
            String defXmlFileName = "ScadaCommSvcConfigTCP.xml";
            if (path.equals("") && filename.equals("")) {
                Path file = Paths.get(defXmlFileName);
                Files.write(file, Collections.singleton(result), StandardCharsets.UTF_8);
                System.out.println("xml file was created");
            }
            if (path.equals("") && !filename.equals("")) {
                Path file = Paths.get(filename);
                Files.write(file, Collections.singleton(result), StandardCharsets.UTF_8);
                System.out.println(filename + "xml file was created");
            }
            if (!path.equals("") && filename.equals("")) {
                Path file = Paths.get(path + defXmlFileName);
                Files.write(file, Collections.singleton(result), StandardCharsets.UTF_8);
                System.out.println(filename + "xml file was created");
            }
            if (!path.equals("") && !filename.equals("")) {
                Path file = Paths.get(path + filename);
                Files.write(file, Collections.singleton(result), StandardCharsets.UTF_8);
                System.out.println(filename + "xml file was created");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
