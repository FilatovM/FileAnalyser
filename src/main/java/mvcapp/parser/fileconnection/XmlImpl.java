package mvcapp.parser.fileconnection;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvcapp.entities.Requirement;
import org.xml.sax.SAXException;

public class XmlImpl implements  FileDAO {
    @Override
    public List<Requirement> parseReqs(String path) throws ParserConfigurationException, IOException, SAXException, ParseException {
        File xmlFile = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());

        List<Requirement> reqs = new ArrayList<>();
        for(int tmp = 0; tmp < nodeList.getLength(); tmp++)
        {
            Node node = nodeList.item(tmp);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Requirement req = new Requirement();

                Element element = (Element)node;
                req.setId(Integer.valueOf(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue()));
                req.setTitle(element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());
                req.setText(element.getElementsByTagName("text").item(0).getChildNodes().item(0).getNodeValue());
                req.setComment(element.getElementsByTagName("comment").item(0).getChildNodes().item(0).getNodeValue());
                String done = element.getElementsByTagName("done").item(0).getChildNodes().item(0).getNodeValue();
                req.setDone(done.equals("yes"));
                String dateStr = (element.getElementsByTagName("date").item(0).getChildNodes().item(0).getNodeValue());
                SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
                Date date = format.parse(dateStr);
                req.setDate(date);

                reqs.add(req);
            }
        }
        return reqs;
    }
}
