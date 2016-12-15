package mvcapp.dbutils.dao;

import mvcapp.entities.Requirement;

import java.sql.SQLException;
import java.util.List;

public interface RequirementDAO {
    void loadReqs(List<Requirement> reqs) throws SQLException;
    List<Requirement> getReqs(String parametr, String contains);
    List<Requirement> getAllReqs();
}
