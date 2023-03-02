package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "rootroot";
    private Connection connection;

    public Connection getConnection(){
         this.connection = null;
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION FAILED");
        }
        return this.connection;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
