package org.example.webapps;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pet_adoption";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "rootpass";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Retrieve signup form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input (you can add more validation)
        if (username == null || email == null || password == null ||
                username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("signup.jsp?error=invalid");
            return;
        }

        // Database connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // SQL query to insert user data
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                // Set parameters
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);

                // Execute the query
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    // Signup successful, redirect to login page
                    response.sendRedirect("login.jsp");
                } else {
                    // Signup failed, redirect back to signup page with error message
                    response.sendRedirect("signup.jsp?error=dberror");
                }
            }
        } catch (SQLException e) {
            // Handle database connection error
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=dberror");
        }
    }
}