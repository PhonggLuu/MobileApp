package com.application.model;

import android.database.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/PRM392";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345";

    private Connection connect() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("Driver loaded successfully");
            Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/PRM392", "postgres", "12345");
            return con;
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "PosgreSQL Driver not found", e);
            throw e;
        }
    }

    public boolean login(String email, String password) throws Exception {
        String query = "SELECT * FROM KH_tbl WHERE ID_kh = ? AND pass_kh = ?";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                // If a record exists, login is successful
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during login", e);
            throw e; // Rethrow the exception for handling upstream
        }
    }

}
