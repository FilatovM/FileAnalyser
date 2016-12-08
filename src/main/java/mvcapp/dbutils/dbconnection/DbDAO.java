package mvcapp.dbutils.dbconnection;

import mvcapp.entities.Requirement;

import java.sql.SQLException;
import java.util.List;

public interface DbDAO {
    void loadReqs(List<Requirement> reqs) throws SQLException;
    List<Requirement> getReqs(String parametr, String contains);
    List<Requirement> getAllReqs();
    void loadUser(String username, String password) throws  SQLException;
    void addRole(String username, String user_role) throws  SQLException;
}
