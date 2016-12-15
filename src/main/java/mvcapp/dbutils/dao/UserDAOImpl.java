package mvcapp.dbutils.dao;

import java.sql.SQLException;

import mvcapp.entities.Role;
import mvcapp.entities.User;
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
