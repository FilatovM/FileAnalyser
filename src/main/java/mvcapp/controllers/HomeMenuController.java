package mvcapp.controllers;

import mvcapp.dbutils.service.DbService;
import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.XmlDAOImpl;
import mvcapp.parser.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeMenuController {
    @Autowired
    private DbService dbService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUser() {
        return "add-user";
    }

    @RequestMapping(value = "/appoint-user", method = RequestMethod.GET)
    public String appointUser() {
        return "appoint-user";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load() {
        return "load";
    }

    @RequestMapping(value = "/load-file", method = RequestMethod.GET)
    public ModelAndView loadFile(String path, String id, String title, String text, String comment
            , String done, String time, String date) throws Exception {
        ModelAndView err = new ModelAndView("/loading-error");
        try {
            File file = new File(path);
            String extension = path.substring(path.length() - 3, path.length());
            FileService fileService;
            switch(extension){
                case "xml":
                    fileService = new FileService(new XmlDAOImpl());
                    break;
                default:
                    err.addObject("message", "Your file is not supported. Use .xml files to load");
                    return err;
            }
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("title", title);
            map.put("text", text);
            map.put("comment", comment);
            map.put("done", done);
            map.put("time", time);
            map.put("date", date);

            List<Requirement> reqs = fileService.parseReqs(file, map);
            dbService.loadReqs(reqs);

            ModelAndView mav = new ModelAndView("/loading-completed");
            mav.addObject("message", "Your file has been successfully loaded.");
            return mav;
        } catch (FileNotFoundException e){
            e.printStackTrace();
            err.addObject("message", "Your file was not found. Check your path.");
            return err;
        } catch (Exception e){
            e.printStackTrace();
            err.addObject("message", e.getMessage());
            return err;
        }
    }

    @RequestMapping(value = "/search-reqs", method = RequestMethod.GET)
    public ModelAndView searchReqs(String parameter, String contains) throws Exception {
        List<Requirement> reqs = dbService.getReqs(parameter, contains);
        ModelAndView mav = new ModelAndView("search-result");
        mav.addObject("reqs", reqs);
        return mav;
    }

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public ModelAndView review() throws Exception {
        List<Requirement> reqs = dbService.getAllReqs();
        ModelAndView mav = new ModelAndView("review");
        mav.addObject("reqs", reqs);
        return mav;
    }

    @RequestMapping(value = "/load-user", method = RequestMethod.GET)
    public ModelAndView loadUser(String username, String password, Integer role_idx) throws Exception {
        ModelAndView err = new ModelAndView("/loading-error");
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            dbService.loadUser(username, hashedPassword);
            ArrayList<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            roles.add("ROLE_MODER");
            roles.add("ROLE_ADMIN");
            for(int tmp = 0; tmp < role_idx; tmp++)
                dbService.addRole(username, roles.get(tmp));

            ModelAndView mav = new ModelAndView("/loading-completed");
            mav.addObject("message", "New user have been successfully added.");
            return mav;
        } catch (Exception e){
            e.printStackTrace();
            err.addObject("message", e.getMessage());
            return err;
        }
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.GET)
    public ModelAndView addRole(String username, String role) throws Exception {
        ModelAndView err = new ModelAndView("/loading-error");
        try {
            dbService.addRole(username, "ROLE_MODER");
            if(role.equals("ROLE_ADMIN"))
                dbService.addRole(username, "ROLE_ADMIN");

            ModelAndView mav = new ModelAndView("/loading-completed");
            mav.addObject("message", "Access granted");
            return mav;
        } catch (Exception e){
            e.printStackTrace();
            err.addObject("message", e.getMessage());
            return err;
        }
    }

}