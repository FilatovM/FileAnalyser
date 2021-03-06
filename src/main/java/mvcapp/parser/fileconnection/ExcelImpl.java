package mvcapp.parser.fileconnection;

import mvcapp.entities.Requirement;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelImpl implements FileDAO{

    @Override
    public List<Requirement> parseReqs(String path, Map<String, String> map) throws Exception {
        List<Requirement> reqs = new ArrayList<>();
        try {
            Workbook wb = WorkbookFactory.create(new File(path));

            Sheet sheet = wb.getSheetAt(0);

            ArrayList<String> fields = new ArrayList<>();
            Iterator<Row> rows = sheet.iterator();
            Row row = rows.next();

            for (Cell cell : row) {
                String field = cell.getStringCellValue();
                fields.add(field);
            }

            while(rows.hasNext()){
                Requirement req = new Requirement();
                HashMap<String, Object> field_val = new HashMap<>();

                row = rows.next();
                Integer idx = 0;
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            field_val.put(fields.get(idx), cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            field_val.put(fields.get(idx), cell.getNumericCellValue());
                            break;
                        default:

                    }
                    idx++;
                }

                if(map.get("id") != null && !map.get("id").isEmpty())
                    req.setId(((Double) field_val.get(map.get("id"))).intValue());

                if(map.get("title") != null && !map.get("title").isEmpty())
                    req.setTitle((String) field_val.get(map.get("title")));

                if(map.get("text") != null && !map.get("text").isEmpty())
                    req.setText((String) field_val.get(map.get("text")));

                if(map.get("comment") != null && !map.get("comment").isEmpty())
                    req.setComment((String) field_val.get(map.get("comment")));

                if(map.get("done") != null && !map.get("done").isEmpty()) {
                    String field = map.get("done");
                    String done = (String) field_val.get(field);
                    done = done.toLowerCase();
                    HashSet<String> yes = new HashSet<>();
                    yes.add("yes");
                    yes.add("y");
                    yes.add("true");
                    yes.add("+");
                    req.setDone(yes.contains(done));
                }

                if(map.get("time") != null && !map.get("time").isEmpty())
                    req.setTime(((Double) field_val.get(map.get("time"))).intValue());

                if(map.get("date") != null && !map.get("date").isEmpty()) {
                    String field = map.get("date");
                    String dateStr = (String) field_val.get(field);
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = format.parse(dateStr);
                    req.setDate(date);
                }

                reqs.add(req);
            }
            return reqs;
        }catch (IOException e){
            e.printStackTrace();
            return reqs;
        }

    }
}
