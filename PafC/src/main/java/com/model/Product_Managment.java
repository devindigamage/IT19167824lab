package com.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Product_Managment {

	//method to connect database
	
	private Connection connect() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/product_managment", "SamithraPaf", "M_inu1998@#");
			System.out.println("Successfully Connected");
			
		}catch(Exception e) {
			System.out.println(e);
			
			e.printStackTrace();
		}
		return con;
	}
	
	public String addProduct(String productId,String productName,String AstimatedValue,String ProductDescription) {
		
		String output = ""; 
		
		try {
			Connection con = connect();
			
			if(con == null) { 
				return "Error while connecting to the database for inserting applicationt details.";
			}
			
			String query = " insert into productdetails (productId,productName,AstimatedValue,ProductDescription)" + " values (?,?,?,?)";
			
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding product details
			 preparedStmt.setString(1, productId);
			 preparedStmt.setString(2, productName); 
			 preparedStmt.setString(3, AstimatedValue);
			 preparedStmt.setString(4, ProductDescription);
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close();
			 
			 output = "Inserted Successfully!";
			 
		}
		catch(Exception e) {
			output = "Error while inserting the product details.";
			System.err.println(e.getMessage());
			
		}
		return output; 
	}
	
	//read products from the system
	public String readProducts() {
		
		String output = ""; 
		
		try {
			
			Connection con = connect();
			if (con == null) {

				return "Error while connecting to the database for reading the available products.";
				
				
			}
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>Product Id</th><th>Product Name</th>" +
					 "<th>Astimated Value</th>" + "<th>Product Description</th>"; 
			
			String query = "select * from productdetails"; 
			
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String productId = rs.getString("productId");
				System.out.println(productId);
				String productName = rs.getString("productName");
				String AstimatedValue = rs.getString("AstimatedValue");
				String ProductDescription = rs.getString("ProductDescription");
				
				output += "<tr><td>" + productId  + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + AstimatedValue + "</td>";
				output += "<td>" + ProductDescription + "</td></tr>";
				
			}
			con.close();
			output += "</table>";
			
		}
		catch(Exception e) {
			output = "An error occurred while reading the product details. ";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//update existing product details
	public String updateProducts(String productId, String productName, String AstimatedValue, String ProductDescription) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null) {

				return "Error occured while updating the product details.";
			}
			
			String query = "UPDATE productdetails SET productName=?,AstimatedValue=?,ProductDescription=?  WHERE productId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values to query
			preparedStmt.setString(1, productName); 
			preparedStmt.setString(2, AstimatedValue); 
			preparedStmt.setString(3, ProductDescription); 
			preparedStmt.setString(4, productId); 
			
			preparedStmt.execute(); 
			 con.close();
			 
			 output = "Product details update successfully.";
			
		}
		catch(Exception e) {
			
			output = "An error occurred while updating the product details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete the products
	public String deleteProduct(String productId) {
		
		String output = ""; 
		
		try {
			
			Connection con = connect(); 
			if (con == null) {

				return "Error occured while deleting the Products.";
			}
			
			String query = "delete from productdetails where productId=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, productId); 
			
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Product details deleted successfully";
		}
		catch(Exception e) {
			output = " An error occurred while deleting the product details.";
			System.err.println(e.getMessage());
			
		}
		return output;
	}
}
