package mvcapp.dbutils.service;

import java.sql.SQLException;
import java.util.List;

import mvcapp.dbutils.dbconnection.DataBaseDAO;
import mvcapp.entities.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBService {
    private DataBaseDAO dataBase_dao;

    public DBService(DataBaseDAO dataBase_dao){
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

}