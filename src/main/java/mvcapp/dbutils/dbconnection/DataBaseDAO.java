package mvcapp.dbutils.dbconnection;

import mvcapp.entities.Requirement;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseDAO {
    public void loadReqs(List<Requirement> reqs) throws SQLException;
}
