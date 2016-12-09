package mvcapp.parser.service;

import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.FileDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class FileService {
    private FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Transactional
    public List<Requirement> parseReqs(String path, Map<String, String> map) throws Exception {
        return fileDAO.parseReqs(path, map);
    }
}
