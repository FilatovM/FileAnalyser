package mvcapp.DB_Utils.DAO;

import mvcapp.Entities.Requirement;

import java.util.List;

public interface DB_DAO {
    public void loadReqs(List<Requirement> reqs);
}
