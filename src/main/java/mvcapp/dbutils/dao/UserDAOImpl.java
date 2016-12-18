package mvcapp.dbutils.dao;

import java.sql.SQLException;
import java.util.List;

import mvcapp.entities.Role;
import mvcapp.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void loadUser(String username, String password) throws SQLException{
        User user = new User(username, password);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.username like :val");
        query.setParameter("val", username);
        List<User> users = query.list();
        if(users.isEmpty())
            session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void addRole(String username, String user_role) throws SQLException{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select id from User u where u.username like :val");
        query.setParameter("val", username);
        Integer id = (Integer) query.list().get(0);
        Role role = new Role(id, user_role);
        query = session.createQuery("from Role r where r.user_id = :val1 AND r.role like :val2");
        query.setParameter("val1", id);
        query.setParameter("val2", user_role);
        List<Role> users = query.list();
        if(users.isEmpty())
            session.save(role);
        session.save(role);
        session.getTransaction().commit();
    }
}
