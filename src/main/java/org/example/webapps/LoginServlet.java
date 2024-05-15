package org.example.webapps;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

        // Retrieve login form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input (you can add more validation)
        if (username == null || password == null ||
                username.isEmpty() || password.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("login.jsp?error=invalid");
            return;
        }

        // Database connection
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // SQL query to check user credentials
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                // Set parameters
                statement.setString(1, username);
                statement.setString(2, password);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // Login successful, store user details in session and redirect to dashboard
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("dashboard.jsp");
                } else {
                    // Login failed, redirect back to login page with error message
                    response.sendRedirect("login.jsp?error=invalid");
                }
            }
        } catch (SQLException e) {
            // Handle database connection error
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=dberror");
        }
    }
}



