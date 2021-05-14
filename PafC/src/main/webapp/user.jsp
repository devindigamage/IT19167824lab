<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
 <%@ page import="com.model.Account_Management"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Details</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="jquery-3.6.0.js"></script>

<script>
		
$(document).ready(function() {

	if ($("#alertSuccess").text().trim() == "") {

		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
		//$("#formFund").submit();
	var type = ($("#hidIdAccSave").val() == "") ? "POST" : "PUT";
	//alert($("#hidIdAccSave").val());
	$.ajax(
		{
			 url : "AccountManagementAPI",
			 type : type,
			 data : $("#formAccount").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 	onItemSaveComplete(response.responseText, status);
			 }
	});
});


	function onItemSaveComplete(response, status) {
		
		if (status == "success") {
			
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success") {
				
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divFundsGrid").html(resultSet.data);
				
			} else if (resultSet.status.trim() == "error") {
				
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
			
		}else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		
		$("#hidIdAccSave").val("");
		$("#formAccount")[0].reset();
	}

	//UPDATE==========================================
	$(document).on("click",".btnUpdate",function(event) {
					$("#hidIdAccSave").val($(this).data("idAccount"));
					$("#Id").val($(this).closest("tr").find('td:eq(0)').text());
					$("#Acc_name").val($(this).closest("tr").find('td:eq(1)').text());
					$("#Email").val($(this).closest("tr").find('td:eq(2)').text());
					$("#Phone").val($(this).closest("tr").find('td:eq(3)').text());
					$("#Password").val($(this).closest("tr").find('td:eq(4)').text());
	});
	
	function onItemDeleteComplete(response, status)
	{
		if (status == "success")
		 {
		 var resultSet = JSON.parse(response);
		 
		 if (resultSet.status.trim() == "success")
		 {
				 $("#alertSuccess").text("Successfully deleted.");
				 $("#alertSuccess").show();
				 
				 $("#divFundsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
				 $("#alertError").text(resultSet.data);
				 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
				 $("#alertError").text("Error while deleting.");
				 $("#alertError").show();
		 } else
		 {
				 $("#alertError").text("Unknown error while deleting..");
				 $("#alertError").show();
		 }
	}
	
	//DELETE==========================================
	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
			 url : "AccountManagementAPI",
			 type : "DELETE",
			 data : "id=" + $(this).data("Id"),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 		onAccountDeleteComplete(response.responseText, status);
			 }
 		});
	});

	// CLIENT-MODEL================================================================
	function validateFundForm() {

		// PROJECT ID
		if ($("#Id").val().trim() == "") {
			return "Insert Account ID.";
		}

		// RESEARCHER ID
		if ($("#Acc_name").val().trim() == "") {
			return "Insert Account Name.";
		}

		// CLIENT ID
		if ($("Email#").val().trim() == "") {
			return "Insert Email.";
		}

		// AMOUNT-------------------------------
		if ($("#Phone").val().trim() == "") {
			return "Insert phone.";
		}
		
		// STATUS------------------------
		if ($("#status").val().trim() == "") {
			return "Insert Account Status.";
		}
		return true;
	}
</script>




</head>
<body>

<div class="row">
		<div class="col-5">
		
		<h1 class="m-3">Account details</h1>
		
		<form id="formAccount" name="formAccount" method="post" action="user.jsp">
		 Account ID: 
		<input id="accountId" name="accountId" type="text" 
		 class="form-control form-control-sm">
		<br> Account Name: 
		<input id="accountName" name="accountName" type="text" 
		 class="form-control form-control-sm">
		<br> Phone: 
		<input id="phone" name="phone" type="text" 
		 class="form-control form-control-sm">
		 <br> Email:
		 <input id="email" name="email" type="text" 
		 class="form-control form-control-sm">
		<br> password: 
		<input id="password" name="password" type="text" 
		 class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" 
		 class="btn btn-primary"><br>
		<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divFundsGrid">
				<%
					Account_Management f = new Account_Management();
					out.print(f.readAccount());
				%>
			</div>
		
		</div>
	</div>
</div>
</body>
</html>