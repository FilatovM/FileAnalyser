package mvcapp.parser.fileconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import mvcapp.entities.Requirement;
import org.xml.sax.SAXException;

@Repository
public class XmlImpl implements  FileDAO {
    private static Logger log = LoggerFactory.getLogger(XmlImpl.class);

    @Override
    public List<Requirement> parseReqs(String path, Map<String, String> map) throws ParserConfigurationException, IOException, SAXException, ParseException {
        List<Requirement> reqs = new ArrayList<>();
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());


            for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                Node node = nodeList.item(tmp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Requirement req = new Requirement();
                    Element element = (Element) node;

                    if (map.get("title") != null && !map.get("title").isEmpty())
                        req.setTitle(element.getElementsByTagName(map.get("title")).item(0).getChildNodes().item(0).getNodeValue());

                    if (map.get("text") != null && !map.get("text").isEmpty())
                        req.setText(element.getElementsByTagName(map.get("text")).item(0).getChildNodes().item(0).getNodeValue());

                    if (map.get("comment") != null && !map.get("comment").isEmpty())
                        req.setComment(element.getElementsByTagName(map.get("comment")).item(0).getChildNodes().item(0).getNodeValue());

                    if (map.get("done") != null && !map.get("done").isEmpty()) {
                        String done = element.getElementsByTagName(map.get("done")).item(0).getChildNodes().item(0).getNodeValue();
                        done = done.toLowerCase();
                        HashSet<String> yes = new HashSet<>();
                        yes.add("yes");
                        yes.add("y");
                        yes.add("true");
                        yes.add("+");
                        req.setDone(yes.contains(done));
                    }

                    if (map.get("time") != null && !map.get("time").isEmpty())
                        req.setTime(Integer.valueOf(element.getElementsByTagName(map.get("time")).item(0).getChildNodes().item(0).getNodeValue()));

                    if (map.get("date") != null && !map.get("date").isEmpty()) {
                        String dateStr = (element.getElementsByTagName(map.get("date")).item(0).getChildNodes().item(0).getNodeValue());
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        Date date = format.parse(dateStr);
                        req.setDate(date);
                    }

                    reqs.add(req);
                }
            }
            return reqs;
        } catch (Exception e){
            log.error("Parsing error", e);
            return reqs;
        }
    }
}
