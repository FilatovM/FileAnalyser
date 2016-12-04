package mvcapp.controllers;

import mvcapp.dbutils.dbconnection.DataBasePostgresImpl;
import mvcapp.dbutils.service.DBService;
import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.XmlImpl;
import mvcapp.parser.service.FileService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class HomeMenuController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load() {
        return "load";
    }

    @RequestMapping(value = "/load-file", method = RequestMethod.GET)
    public String loadFile(String path) throws Exception {

        String extension = path.substring(path.length() - 3, path.length());
        FileService fileService;
        switch(extension){
            case "xml":
                fileService = new FileService(new XmlImpl());
                break;
            default:
                return "loading-error";
        }

        List<Requirement> reqs = fileService.parseReqs(path);

        return "load-completed";
    }


}