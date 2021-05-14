package com.restProject.PafC;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Account_Management;


@Path("Account_Management_Service")
public class Account_Management_Service {

	Account_Management Obj = new Account_Management();
	//Get METHOD
  @GET
  @Path("get")
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt() {
	 return "Got it dew!";
	       
 }
  
  
//Get method for fund
  @GET
	@Path("getAccounts")
	@Produces(MediaType.TEXT_HTML)
	public String readAccount()
	{
		return Obj.readAccount();
	}
  
  
//POST METHOD FOR NEW ACCOUNT
 // @POST 
//	@Path("AccApplication") 
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
//	@Produces(MediaType.TEXT_PLAIN)
	//public String insertApplication( @FormParam("ID") int ID,
	//		                @FormParam("AccountName") String accountName,
		//					@FormParam("email") String email,
		//					@FormParam("phone") String phone,
		//					@FormParam("password") String password)
							
							
	//{
	//	String output = Obj.insertAccApplication(ID,accountName, email, phone, password);  
	//	return output; 
	//}
  
//PUT METHOD
 // @PUT 
	//@Path("UpdateAccount") @Consumes(MediaType.APPLICATION_JSON) 
//	@Produces(MediaType.TEXT_PLAIN)
	//public String updateAccount(String acc) {  
	//	JsonObject Object = new JsonParser().parse(acc).getAsJsonObject(); 
		 
	//	String Id = Object.get("id").getAsString();  
	//	String Accname = Object.get("acc_name").getAsString();  
	//	String Email = Object.get("email").getAsString();  
	//	String Phone = Object.get("phone").getAsString();
		//String Password = Object.get("password").getAsString();
		
		
		 
	//	String output = Obj.updateAccount(Id,Accname, Email, Phone, Password); 
		 
	//	 return output; 
	//	 
	//}
  
  
  //DELETE METHOD
  @DELETE 
	@Path("DeleteAcc") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAccount(String acc) {  
		
		Document doc = Jsoup.parse(acc, "", Parser.xmlParser());  
		String id = doc.select("id").text(); 
		 
		 String output = Obj.deleteAccount(id);
		 
		 return output; 
 }
  

  
  
}
