package mvcapp.controllers;

import mvcapp.dbutils.service.RequirementService;
import mvcapp.dbutils.service.UserService;
import mvcapp.entities.Requirement;
import mvcapp.parser.fileconnection.JsonImpl;
import mvcapp.parser.fileconnection.ExcelImpl;
import mvcapp.parser.fileconnection.XmlImpl;
import mvcapp.parser.service.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger log = LoggerFactory.getLogger(HomeMenuController.class);

    @Autowired
    private RequirementService requirementService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search/search";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUser() {
        return "administrating/add-user";
    }

    @RequestMapping(value = "/appoint-user", method = RequestMethod.GET)
    public String appointUser() {
        return "administrating/appoint-user";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load() {
        return "loading/load";
    }

    @RequestMapping(value = "/load-file", method = RequestMethod.GET)
    public ModelAndView loadFile(String path, String id, String title, String text, String comment
            , String done, String time, String date) throws Exception {
        ModelAndView err = new ModelAndView("loading/loading-error");
        try {
            File file = new File(path);
            String extension = path.substring(path.length() - 4, path.length());
            FileService fileService;
            switch(extension){
                case ".xml":
                    fileService = new FileService(new XmlImpl());
                    break;
                case "json":
                    fileService = new FileService(new JsonImpl());
                    break;
                case ".xls":
                case "xlsx":
                    fileService = new FileService(new ExcelImpl());
                    break;
                default:
                    err.addObject("message", "Your file is not supported. Use .xml, .xls, .xlsx, .json files to load");
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

            List<Requirement> reqs = fileService.parseReqs(path, map);
            requirementService.loadReqs(reqs);

            ModelAndView mav = new ModelAndView("loading/loading-completed");
            mav.addObject("message", "Your file has been successfully loaded.");
            return mav;
        } catch (FileNotFoundException e){
            log.error("File not found error", e);
            err.addObject("message", "Your file was not found. Check your path.");
            return err;
        } catch (Exception e){
            log.error("Requirement loading error", e);
            err.addObject("message", e.getMessage());
            return err;
        }
    }

    @RequestMapping(value = "/search-reqs", method = RequestMethod.GET)
    public ModelAndView searchReqs(String parameter, String contains) throws Exception {
        List<Requirement> reqs = requirementService.getReqs(parameter, contains);
        ModelAndView mav = new ModelAndView("search/search-result");
        mav.addObject("reqs", reqs);
        return mav;
    }

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public ModelAndView review() throws Exception {
        List<Requirement> reqs = requirementService.getAllReqs();
        ModelAndView mav = new ModelAndView("search/review");
        mav.addObject("reqs", reqs);
        return mav;
    }

    @RequestMapping(value = "/load-user", method = RequestMethod.GET)
    public ModelAndView loadUser(String username, String password, Integer role_idx) throws Exception {
        ModelAndView err = new ModelAndView("loading/loading-error");
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            userService.loadUser(username, hashedPassword);
            ArrayList<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            roles.add("ROLE_MODER");
            roles.add("ROLE_ADMIN");
            for(int tmp = 0; tmp < role_idx; tmp++)
                userService.addRole(username, roles.get(tmp));

            ModelAndView mav = new ModelAndView("loading/loading-completed");
            mav.addObject("message", "New user have been successfully added.");
            return mav;
        } catch (Exception e){
            log.error("User loading error", e);
            err.addObject("message", e.getMessage());
            return err;
        }
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.GET)
    public ModelAndView addRole(String username, String role) throws Exception {
        ModelAndView err = new ModelAndView("loading/loading-error");
        try {
            if(role.equals("ROLE_ADMIN"))
                userService.addRole(username, "ROLE_ADMIN");
            userService.addRole(username, "ROLE_MODER");

            ModelAndView mav = new ModelAndView("loading/loading-completed");
            mav.addObject("message", "Access granted");
            return mav;
        } catch (Exception e){
            log.error("Role loading error", e);
            err.addObject("message", e.getMessage());
            return err;
        }
    }

}