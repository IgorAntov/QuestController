package org.autoquest.service.rscadaproject;

import org.autoquest.quest.view.Graphic;
import org.autoquest.quest.view.Group;
import org.autoquest.quest.view.IGraphic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class InterfaceXML {

    private final String path;
    ;
    public InterfaceXML(String path) {
        this.path = path;
    }

    public void build() {
        Path file = Paths.get(path + "/BaseXML/Interface.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfInterface xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");
        int index = 1;
        for (IGraphic group: Graphic.getInstance().getElements()) {
            group.getContent(sb, index++);
        }
        sb.append("</ArrayOfInterface>");
        return sb;
    }
}
