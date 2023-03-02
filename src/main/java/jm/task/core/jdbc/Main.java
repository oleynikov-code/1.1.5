package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Bob", "Marley", (byte) 36);
        userDaoHibernate.saveUser("Tony", "Montana", (byte) 40);
        userDaoHibernate.saveUser("Carl", "Cox", (byte) 60);
        userDaoHibernate.saveUser("Biatrix", "Kiddo", (byte) 30);
        System.out.println(userDaoHibernate.getAllUsers());
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();
    }
}
