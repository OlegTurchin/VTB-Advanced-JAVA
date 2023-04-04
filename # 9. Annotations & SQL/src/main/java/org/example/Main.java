package org.example;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "0707";

    public static void main(String[] args) {
        TargetClass tCl1 = new TargetClass("Bob", 32, 72_000, true);
        TargetClass tCl2 = new TargetClass("Derek", 21, 23_000, false);
        TargetClass tCl3 = new TargetClass("Henry", 42, 213_000, true);

        try {
            connect();
            DBProcessor.tableBiulder(TargetClass.class);
//            DBProcessor.tableFiller(tCl1);
//            DBProcessor.tableFiller(tCl2);
//            DBProcessor.tableFiller(tCl3);
            DBProcessor.tableFiller2(tCl1);
            DBProcessor.tableFiller2(tCl2);
            DBProcessor.tableFiller2(tCl3);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        try {
            DBProcessor.connection = DriverManager.getConnection(url,user,password);
            DBProcessor.statement = DBProcessor.connection.createStatement();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Unable to connect.");
        }
    }

    public static void disconnect() {
        try {
            DBProcessor.statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DBProcessor.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}