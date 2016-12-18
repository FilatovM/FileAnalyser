package mvcapp.parser.fileconnection;

import mvcapp.entities.Requirement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonImpl implements  FileDAO {
    private static Logger log = LoggerFactory.getLogger(JsonImpl.class);

    @Override
    public List<Requirement> parseReqs(String path, Map<String, String> map) {
        List<Requirement> reqs = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            Requirement req = new Requirement();

            if(map.get("title") != null && !map.get("title").isEmpty())
                req.setTitle((String) jsonObject.get(map.get("title")));

            if(map.get("text") != null && !map.get("text").isEmpty())
                req.setText((String) jsonObject.get(map.get("text")));

            if(map.get("comment") != null && !map.get("comment").isEmpty())
                req.setComment((String) jsonObject.get(map.get("comment")));

            if(map.get("done") != null && !map.get("done").isEmpty()) {
                String done = (String) jsonObject.get(map.get("done"));
                done = done.toLowerCase();
                HashSet<String> yes = new HashSet<>();
                yes.add("yes");
                yes.add("y");
                yes.add("true");
                yes.add("+");
                req.setDone(yes.contains(done));
            }

            if(map.get("time") != null && !map.get("time").isEmpty())
                req.setTime(((Long) jsonObject.get(map.get("time"))).intValue());

            if(map.get("date") != null && !map.get("date").isEmpty()) {
                String dateStr = (String) jsonObject.get(map.get("date"));
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date = format.parse(dateStr);
                req.setDate(date);
            }

            reqs.add(req);
            return reqs;
        }catch (Exception e){
            log.error("Parsing error", e);
            return reqs;
        }

    }
}
