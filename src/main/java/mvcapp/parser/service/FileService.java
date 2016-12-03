package mvcapp.parser.service;

import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileDAO fileDAO;

    @Transactional
    public List<Requirement> parseReqs(String path) throws ParserConfigurationException, SAXException, ParseException, IOException {
        return fileDAO.parseReqs(path);
    }
}
