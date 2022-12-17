package org.autoquest.service.rscadaproject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class ObjCreating {

    private final String projectName;
    private final String path;
;
    public ObjCreating(String projectName, String path) {
        this.projectName = projectName;
        this.path = path;
    }

    public void build() {
        Path file = Paths.get(path + "/BaseXML/Obj.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                        <ArrayOfObj xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                        <Obj>
                        <ObjNum>1</ObjNum>
                        <Name>""")
                .append(this.projectName)
                .append("""
                        </Name>
                        </Obj>
                        </ArrayOfObj>""");
        return sb;
    }
}
