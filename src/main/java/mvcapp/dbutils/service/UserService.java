package mvcapp.dbutils.service;

import java.sql.SQLException;

import mvcapp.dbutils.dao.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Transactional
    public void loadUser(String username, String password) throws SQLException{
        userDAO.loadUser(username, password);
    }

    @Transactional
    public void addRole(String username, String user_role) throws SQLException{
        userDAO.addRole(username, user_role);
    }
}