package org.autoquest.service.state;

import org.autoquest.connections.MBParameter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadXML {

    public static void readParametersFromXML(ArrayList<MBParameter> mbParameters) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(WriteXML.class.getResource("/qstate.xml").getFile()));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("parameter");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    if (element.getTextContent().equals("enable")) {
                        mbParameters.get(Integer.parseInt(id)).farceValue(true);
                    } else {
                        mbParameters.get(Integer.parseInt(id)).farceValue(false);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
