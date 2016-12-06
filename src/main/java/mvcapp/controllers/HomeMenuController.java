package mvcapp.controllers;

import mvcapp.dbutils.dbconnection.DataBaseDAO;
import mvcapp.dbutils.dbconnection.DataBasePostgresImpl;
import mvcapp.dbutils.service.DBService;
import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.XmlImpl;
import mvcapp.parser.service.FileService;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeMenuController {
    @Autowired
    private DBService dbService;

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
        dbService.loadReqs(reqs);
        return "load-completed";
    }

    @RequestMapping(value = "/search-reqs", method = RequestMethod.GET)
    public ModelAndView searchReqs(String parameter, String contains) throws Exception {
        List<Requirement> reqs = dbService.getReqs(parameter, contains);
        ModelAndView mav = new ModelAndView("/search-result");
        mav.addObject("reqs", reqs);
        return mav;
    }

}