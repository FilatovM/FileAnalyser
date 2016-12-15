package mvcapp.dbutils.service;

import mvcapp.dbutils.dao.RequirementDAO;
import mvcapp.entities.Requirement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class RequirementService {
    private RequirementDAO requirementDAO;

    public RequirementService(RequirementDAO requirementDAO) {
        this.requirementDAO = requirementDAO;
    }

    @Transactional
    public void loadReqs(List<Requirement> reqs) throws SQLException {
        requirementDAO.loadReqs(reqs);
    }

    @Transactional
    public List<Requirement> getReqs(String parametr, String contains) throws SQLException{
        return requirementDAO.getReqs(parametr, contains);
    }

    @Transactional
    public List<Requirement> getAllReqs() throws SQLException{
        return requirementDAO.getAllReqs();
    }
}
