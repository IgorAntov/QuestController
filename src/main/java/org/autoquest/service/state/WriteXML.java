package org.autoquest.service.state;

import org.autoquest.connections.MBParameter;
import org.autoquest.quest.StateStore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class WriteXML {

    public static void writeParametersToXML() {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder;
    String stateXMLFileName = "qstate.xml";

        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("state");
            doc.appendChild(rootElement);
            int index = 0;
            for (MBParameter p : StateStore.getParameterStore()) {
                Element parameter = doc.createElement("parameter");
                rootElement.appendChild(parameter);
                parameter.setAttribute("id", String.valueOf(index));
                if (p.getBoolValue()) {
                    parameter.setTextContent("enable");
                } else {
                    parameter.setTextContent("disable");
                }
                index++;
            }

            String dir = WriteXML.class.getResource("/").getFile();
            try (FileOutputStream output = new FileOutputStream(dir + "/" + stateXMLFileName)) {
                try {
                    writeXml(doc, output);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }
}
