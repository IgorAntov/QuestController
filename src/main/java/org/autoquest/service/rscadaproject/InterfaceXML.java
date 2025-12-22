package org.autoquest.service.rscadaproject;

import org.apache.commons.io.FileUtils;
import org.autoquest.quest.view.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

public class InterfaceXML {

    private final String path;

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
        for (Group group : Graphic.getInstance().getElements()) {
            group.getContent(sb, index++);
            for (Screen screen : group.getElements()) {
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
        for (Group group : Graphic.getInstance().getElements()) {
            for (Screen screen : group.getElements()) {
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
                sb.append("<Images>\n");
                for (String imageName : screen.getImages()) {
                    sb.append("<Image>");
                    sb.append("<Name>" + imageName +  "</Name>");
                    try {
                        sb.append("<Data>" + convertFileToBase64(imageName) +  "</Data>");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    sb.append("</Image>");
                }
                sb.append("</Images>\n");
                sb.append("</SchemeView>");
                createScreen(screenPath, sb);
                sb.setLength(0);
                componentID = 1;
            }
        }
    }

    public String convertFileToBase64(String filename) throws IOException {
        System.out.println("fileGetResurse:" + filename);
        File fileX =  new File(getClass().getResource("/images/" + filename).getPath());


        //    File file = new File(getClass().getResource("/images/" + filename).getFile());


//        ClassLoader classLoader = getClass().getClassLoader();
//        URL resource = classLoader.getResource("/images/" + filename);
//        System.out.println("Resours: " + resource);

//        byte[] fileContent;
//        if (resource == null) {
//            throw new IllegalArgumentException("file not found! " + fileX);
//        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
//            try {
//                File file = new File(resource.toURI());
        byte[] fileContent = FileUtils.readFileToByteArray(fileX);

//            } catch (URISyntaxException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
