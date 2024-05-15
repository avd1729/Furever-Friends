package org.example.webapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PetServlet")
public class PetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pet_adoption";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "rootpass";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Fetch pet data from database
        List<Pet> pets = fetchPetsFromDatabase();

        // Convert pet data to JSON format
        String jsonPets = convertPetsToJson(pets);

        // Write JSON response
        out.print(jsonPets);
        out.flush();
    }

    private List<Pet> fetchPetsFromDatabase() {
        List<Pet> pets = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish connection to MySQL database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Connected to database"); // Debugging

            // SQL query to fetch pet data
            String sql = "SELECT * FROM Pets";
            pstmt = conn.prepareStatement(sql);

            // Execute the query
            rs = pstmt.executeQuery();
            System.out.println("Executing query"); // Debugging

            // Iterate through the result set and create Pet objects
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                String description = rs.getString("description");
                boolean available = rs.getBoolean("available");
                pets.add(new Pet(name, type, breed, age, description, available));
            }

            System.out.println("Fetched " + pets.size() + " pets"); // Debugging

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pets;
    }


    private String convertPetsToJson(List<Pet> pets) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            jsonBuilder.append("{")
                    .append("\"name\": \"" + pet.getName() + "\",")
                    .append("\"type\": \"" + pet.getType() + "\",")
                    .append("\"breed\": \"" + pet.getBreed() + "\",")
                    .append("\"age\": " + pet.getAge() + ",")
                    .append("\"description\": \"" + pet.getDescription() + "\",")
                    .append("\"available\": " + pet.isAvailable())
                    .append("}");
            if (i < pets.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
