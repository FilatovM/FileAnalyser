package mvcapp.dbutils.dao;

import mvcapp.entities.Requirement;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class RequirementDAOImpl implements RequirementDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void loadReqs(List<Requirement> reqs) throws SQLException {
        for (Requirement req: reqs) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(req);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Requirement> getReqs(String parameter, String contains) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Requirement Req where Req." +
                parameter +
                " like :val");
        query.setParameter("val", contains);
        List<Requirement> reqs = query.list();
        session.getTransaction().commit();
        return reqs;
    }

    @Override
    public List<Requirement> getAllReqs() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Requirement");
        List<Requirement> reqs = query.list();
        session.getTransaction().commit();
        return reqs;
    }

}
