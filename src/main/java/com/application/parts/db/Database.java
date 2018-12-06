package com.application.parts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {

            Class.forName(DRIVER);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }


        return connection;
    }

    /**
     * Creates and populates table with test data
     */
    public static void createAndPopulateDB() {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String sql = "DROP TABLE IF EXISTS parts;\n" +
                "DROP SEQUENCE IF EXISTS seq;\n" +
                "\n" +
                "CREATE SEQUENCE seq START 1;\n" +
                "\n" +
                "CREATE TABLE parts\n" +
                "(\n" +
                "  part_id    INTEGER PRIMARY KEY DEFAULT nextval('seq'),\n" +
                "  part_name       VARCHAR,\n" +
                "  part_number      VARCHAR,\n" +
                "  vendor   VARCHAR,\n" +
                "  qty INTEGER,\n" +
                "  shipped    DATE,\n" +
                "  receive DATE \n" +
                ");\n" +
                "\n" +
                "INSERT INTO parts (part_name, part_number, vendor, qty, shipped, receive) VALUES\n" +
                "  ('part1', 'p001', 'vendor1', 5, '2018-01-01', '2018-01-02'),\n" +
                "  ('part3', 'p003', 'vendor3', 6, '2018-03-01', '2018-03-02'),\n" +
                "  ('part6', 'p006', 'vendor1', 1, '2018-02-01', '2018-02-02'),\n" +
                "  ('part4', 'p004', 'vendor4', 111, '2018-05-01', '2018-05-02'),\n" +
                "  ('part2', 'p002', 'vendor1', 15, '2018-04-01', '2018-04-02'),\n" +
                "  ('part5', 'p005', 'vendor3', 79, '2018-06-01', '2018-06-02');";

        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement(sql);
            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
