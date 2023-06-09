package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.Feedback;


public class FeedbackDao {
	 public int registerFeedback(Feedback feedback) throws Exception {
	        String INSERT_USERS_SQL = "INSERT INTO feedback" +
	            "  ( name, email, message) VALUES " +
	            " (?, ?, ?);";

	        int result = 0;

	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection con=  DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinebooking","root","1234");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, feedback.getName());
	            preparedStatement.setString(2, feedback.getEmail());
	            preparedStatement.setString(3, feedback.getMessage());
	       
	        
	 
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

	}


