package org.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    //String jdbcUrl= ;
    private static final Connection jdbcConnection;

    static {
        try {
            jdbcConnection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/APP_Projact.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // private constructor to avoid client applications using the constructor
    private JDBC(){
    }

    public static Connection getInstance() {
        return jdbcConnection;
    }
}
