package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Grant_management {
	

	//getting mysql connection
	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, user name, password

			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/grant_management",
					"root", "root");
			System.out.println(" Connected Successfully");

		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return con;
	}
	
	//insert grant application details
		public String insertGrantApplication(String id,String title,String full_name, String email, String phone, String research_category,String budget,String introduction) {

			String output = ""; 
			
			try {
				Connection con = connect();

				if (con == null)

				{
					return "Error while connecting to the database for inserting grant applicationt details.";
				}
				// create a prepared statement
				String query = " insert into grant_application (Grant_Application_ID,Title,Full_Name,Email,Phone,Research_category,Budget,Introduction)" + " values (?,?,?,?,?,?,?,?)"; 
				java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values to appointment table
				preparedStmt.setString(1, id);
				preparedStmt.setString(2, title);
				preparedStmt.setString(3, full_name);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5, phone);
				preparedStmt.setString(6, research_category);
				preparedStmt.setString(7, budget);
				preparedStmt.setString(8, introduction);
				
				
				
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully"; 

				
				} 
				catch (Exception e) {
				output = "Error while inserting the application details.";
				System.err.println(e.getMessage());
				}
				return output;

		}
		
		//read Grants from the system
		public String readGrants() {
			String output = "";
			try {

				Connection con = connect();

				if (con == null) {

					return "Error while connecting to the database for reading the Avilable Grants.";

				}
				output = "<table border=\"1\"><tr>" + "<th>Grant ID</th>" + "<th>Title</th>" + "<th>Full Name</th>"
						+ "<th>Email</th>" + "<th>Phone</th>" + "<th>Research Category</th>" + "<th>Budget</th>"+"<th>Introduction</th></tr>";

				String query = "select * from grant_application";

				java.sql.Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					
					String id = rs.getString("Grant_Application_ID");
					System.out.println(id);
					String Title = rs.getString("Title");
				    String Full_Name = rs.getString("Full_Name");
				    String Email = rs.getString("Email");
				    String Phone = rs.getString("Phone");
				    String Research_category = rs.getString("Research_category");
				    String Budget = rs.getString("Budget");
				    String Introduction = rs.getString("Introduction");

					

					output += "<td>" + id  + "</td>";
					output += "<td>" + Title + "</td>";
					output += "<td>" + Full_Name + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + Phone + "</td>";
					output += "<td>" + Research_category + "</td>";
					output += "<td>" +  Budget + "</td>";
					output += "<td>" + Introduction + "</td></tr>";

				}

				con.close();

				output += "</table>";

			} catch (Exception e) {

				output = "An error occurred while reading the grant details. ";
				System.err.println(e.getMessage());

			}

			return output;

		}
		
		//update existing grant
		public String updateGrants(String id,String title, String full_name, String email, String phone,String research_category,String  budget,String introduction)
		{
			
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {

					return "Error occured while updating the Grant details.";
				}

				// create a prepared statement
				
				String query = "UPDATE grant_application SET Title=?,Full_Name=?,Email=?,Phone=?,Research_category=?,Budget=?,Introduction=?  WHERE Grant_Application_ID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 

				
				// binding values to query
				
				preparedStmt.setString(1, title);
				preparedStmt.setString(2, full_name);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, phone);
				preparedStmt.setString(5, research_category);
				preparedStmt.setString(6, budget);
				preparedStmt.setString(7, introduction);
				preparedStmt.setString(8, id);

				// Executeing the statement
				 preparedStmt.execute(); 
				 con.close();

				output = "Grant details update successfully.";
			
			}

			catch (Exception e) {

				output = "An error occurred while updating the grant details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//delete specific grant
	    public String deleteGrants(String id) {
			
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {

					return "Error occured while deleting the Grants.";
				}

				// create a prepared statement
				String query = "delete from grant_application where Grant_Application_ID=?";
				java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, id);

				// executing the statements
				preparedStmt.execute();

				con.close();

				output =  "Grant Details deleted successfully.";
				
			} catch (Exception e) {

				output = " An error occurred while deleting the grant_application details.";
				System.err.println(e.getMessage());
			}
			return output;
		
	    
	    }
		



}
