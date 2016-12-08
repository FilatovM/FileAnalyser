package mvcapp.dbutils.service;

import java.sql.SQLException;
import java.util.List;

import mvcapp.dbutils.dbconnection.DbDAO;
import mvcapp.entities.Requirement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbService {
    private DbDAO dataBase_dao;

    public DbService(DbDAO dataBase_dao){
        this.dataBase_dao = dataBase_dao;
    }

    @Transactional
    public void loadReqs(List<Requirement> reqs) throws SQLException{
        dataBase_dao.loadReqs(reqs);
    }

    @Transactional
    public List<Requirement> getReqs(String parametr, String contains) throws SQLException{
        return dataBase_dao.getReqs(parametr, contains);
    }

    @Transactional
    public List<Requirement> getAllReqs() throws SQLException{
        return dataBase_dao.getAllReqs();
    }

    @Transactional
    public void loadUser(String username, String password) throws SQLException{
        dataBase_dao.loadUser(username, password);
    }

    @Transactional
    public void addRole(String username, String user_role) throws SQLException{
        dataBase_dao.addRole(username, user_role);
    }
}