package mvcapp.dbutils.dbconnection;

import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mvcapp.entities.Requirement;

import java.util.List;

@Component
public class DataBasePostgresImpl implements DataBaseDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void loadReqs(List<Requirement> reqs) throws SQLException{
        for (Requirement req: reqs) {
            sessionFactory.getCurrentSession().save(req);
        }
    }

}
