package org.autoquest.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class CreateXMLForRapidScada {

    private final StringBuilder sb = new StringBuilder();
    public CreateXMLForRapidScada() {
    }

    public void createXMLFile(String path, String filename) {
        sb.append("XML name:\n").append("s line");
        try {
            String defXmlFileName = "ScadaCommSvcConfigTCP.xml";
            if (path.equals("") && filename.equals("")) {
                Path file = Paths.get(defXmlFileName);
                Files.write(file, Collections.singleton(sb), StandardCharsets.UTF_8);
                System.out.println("xml file was created");
            }
            if (path.equals("") && !filename.equals("")) {
                Path file = Paths.get(filename);
                Files.write(file, Collections.singleton(sb), StandardCharsets.UTF_8);
                System.out.println(filename + " file was created");
            }
            if (!path.equals("") && filename.equals("")) {
                Path file = Paths.get(path + defXmlFileName);
                Files.write(file, Collections.singleton(sb), StandardCharsets.UTF_8);
                System.out.println(filename + " file was created");
            }
            if (!path.equals("") && !filename.equals("")) {
                Path file = Paths.get(path + filename);
                Files.write(file, Collections.singleton(sb), StandardCharsets.UTF_8);
                System.out.println(filename + " file was created");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
