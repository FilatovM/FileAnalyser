package mvcapp.controllers;

import mvcapp.dbutils.service.DBService;
import mvcapp.entities.Requirement;
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


        return "load-completed";
    }


}