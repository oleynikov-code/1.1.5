package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERTABLE(ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "NAME VARCHAR (255), LASTNAME VARCHAR (255), AGE BIGINT);");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS USERTABLE;");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            list = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try{
            session = Util.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User ");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if( transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
