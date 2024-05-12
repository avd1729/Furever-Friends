package org.example.webapps;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pet_adoption";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "rootpass";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cat> cats = new ArrayList<>();

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to MySQL database
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Create SQL statement
            String sql = "SELECT * FROM cats";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute SQL query
            ResultSet rs = statement.executeQuery();

            // Process the result set
            while (rs.next()) {
                int petId = rs.getInt("pet_id");
                String petName = rs.getString("pet_name");
                int age = rs.getInt("age");
                String imageUrl = rs.getString("image_url");
                String description = rs.getString("description");

                // Create Cat object and add it to the list
                Cat cat = new Cat(petId, petName, age, imageUrl, description);
                cats.add(cat);
            }

            // Close resources
            rs.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Set cat data as request attribute
        request.setAttribute("cats", cats);

        // Forward to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
