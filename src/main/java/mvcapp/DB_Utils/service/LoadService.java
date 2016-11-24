package mvcapp.DB_Utils.service;

import java.util.List;

import mvcapp.DB_Utils.DAO.DB_DAO;
import mvcapp.Entities.Requirement;

public class LoadService {
    private DB_DAO db_dao;
    public LoadService(DB_DAO db_dao){
        this.db_dao = db_dao;
    }
    public void loadReqs(List<Requirement> reqs) {
        db_dao.loadReqs(reqs);
    }
}