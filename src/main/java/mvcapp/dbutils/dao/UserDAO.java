package mvcapp.dbutils.dao;

import java.sql.SQLException;

public interface UserDAO {
    void loadUser(String username, String password) throws  SQLException;
    void addRole(String username, String user_role) throws  SQLException;
}
