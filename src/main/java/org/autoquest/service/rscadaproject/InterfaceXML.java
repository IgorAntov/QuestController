package org.autoquest.service.rscadaproject;

import org.autoquest.quest.view.Graphic;
import org.autoquest.quest.view.GraphicConfig;
import org.autoquest.quest.view.Group;
import org.autoquest.quest.view.IGraphic;

import java.io.File;
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

    public void createScreen(String screenPath, StringBuilder sb) {
        try {
            File f = new File(path + screenPath + ".sch");
            f.getParentFile().mkdirs();
            f.createNewFile();
            Files.write(f.toPath(), Collections.singleton(sb), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public StringBuilder getXmlContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfInterface xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");
        int index = 1;
        for (IGraphic group : Graphic.getInstance().getElements()) {
            group.getContent(sb, index++);
            for (IGraphic screen : group.getElements()) {
                String screenPath = group.getName() + "/" + screen.getName();
                sb.append("<Interface>\n" +
                        "<ItfID>" + index++ + "</ItfID>\n" +
                        "<Name>" + screenPath + ".sch</Name>\n" +
                        "<Descr>" + screen.getDesc() + "</Descr>\n" +
                        "<Hidden>false</Hidden>\n" +
                        "<ObjNum xsi:nil=\"true\"/>\n" +
                        "</Interface>");
            }
        }
        sb.append("</ArrayOfInterface>");
        return sb;
    }

    public void getScreenXML() {
        StringBuilder sb = new StringBuilder();
        Integer componentID = 1;
        for (IGraphic group : Graphic.getInstance().getElements()) {
            for (IGraphic screen : group.getElements()) {
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<SchemeView title=\"\" xmlns:basic=\"urn:rapidscada:scheme:basic\">\n");
                String screenPath = "/Interface/" + group.getName() + "/" + screen.getName();
                screen.getContent(sb, componentID);
                sb.append("<Components>\n");
                for (IGraphic component : screen.getElements()) {
                    component.getContent(sb, componentID);
                    componentID++;
                }
                sb.append("</Components>\n");
                sb.append("</SchemeView>");
                createScreen(screenPath, sb);
            }
        }
    }

}
