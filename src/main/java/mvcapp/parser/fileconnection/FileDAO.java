package mvcapp.parser.fileconnection;

import mvcapp.entities.Requirement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FileDAO {
    List<Requirement> parseReqs(File file, Map<String, String> map) throws ParserConfigurationException, IOException, SAXException, ParseException;
}
