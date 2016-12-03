package mvcapp.parser.fileconnection;

import mvcapp.entities.Requirement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface FileDAO {
    public List<Requirement> parseReqs(String path) throws ParserConfigurationException, IOException, SAXException, ParseException;
}
