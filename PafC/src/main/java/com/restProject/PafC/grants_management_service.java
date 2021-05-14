package com.restProject.PafC;

import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


import com.model.Grant_management;

@Path("grants_Management_Service")
public class grants_management_service {

	Grant_management Obj = new Grant_management();
	 
	 
	 //Get method for Grants
	  @GET
		@Path("getGrants")
		@Produces(MediaType.TEXT_HTML)
		public String readGrants()
		{
			return Obj.readGrants();
		}
	  
	
	 
	//POST METHOD FOR ADDING NEW GRANT
	  @POST 
		@Path("NewGrant") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN)
		public String insertFund( @FormParam("id") String id,
				                @FormParam("title") String title,
								@FormParam("full_name") String full_name,
								@FormParam("email") String email,
								@FormParam("phone") String phone,
								@FormParam("research_category") String research_category,
								@FormParam("budget") String budget,
								@FormParam("introduction") String introduction)
	
		{
		  	{
		  		String output = Obj.insertGrantApplication(id,title,full_name,email,phone,research_category,budget,introduction);  
		  		return output;
			}
		}
	  
	//PUT METHOD
	    @PUT 
		@Path("UpdateGrants") @Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
		public String updateGrants(String grant) {  
			JsonObject Object = new JsonParser().parse(grant).getAsJsonObject(); 
			 
			String Grant_Application_ID = Object.get("Grant_Application_ID").getAsString();  
			String Title = Object.get("Title").getAsString();  
			String Full_Name = Object.get("Full_Name").getAsString();  
			String Email = Object.get("Email").getAsString();
			String Phone = Object.get("Phone").getAsString();
			String Research_category = Object.get("Research_category").getAsString();
			String Budget = Object.get("Budget").getAsString();
			String Introduction = Object.get("Introduction").getAsString();
			
			 
				
			 
			String output = Obj.updateGrants(Grant_Application_ID,Title, Full_Name, Email, Phone,Research_category,Budget,Introduction); 
			 
			 return output; 
			 
		}
	  
	//DELETE METHOD
	    @DELETE 
		@Path("DeleteGrants") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteGrants(String grant) {  
			
			Document doc = Jsoup.parse(grant, "", Parser.xmlParser());  
			String id = doc.select("id").text(); 
			 
			 String output = Obj.deleteGrants(id);
			 
			 return output; 
			 
	  
	    
	    }
	    
}
