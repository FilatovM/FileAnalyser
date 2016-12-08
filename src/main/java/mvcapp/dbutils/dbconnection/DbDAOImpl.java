package mvcapp.dbutils.dbconnection;

import java.sql.SQLException;

import mvcapp.entities.Role;
import mvcapp.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mvcapp.entities.Requirement;

import java.util.List;

@Repository
public class DbDAOImpl implements DbDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void loadReqs(List<Requirement> reqs) throws SQLException{
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

    @Override
    public void loadUser(String username, String password) throws SQLException{
        User user = new User(username, password);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void addRole(String username, String user_role) throws SQLException{
        Role role = new Role(username, user_role);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
    }
}
