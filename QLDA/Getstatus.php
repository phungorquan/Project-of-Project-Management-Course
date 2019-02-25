<?php

$server 	= "localhost:3306";	// Change this to correspond with your database port
$username 	= "root";			// Change if use webhost online
$password 	= "";
$DB 		= "qlda";			// database name


$conn = new mysqli($server, $username, $password,$DB);		// Check database connection
	if ($conn->connect_error) 
	{
		//die("Connection failed: " . $conn->connect_error);
	} 
	
	$query ="SELECT * from status";					// Select all data in table "status"
	$result = $conn->query($query);
	
		while($row = $result->fetch_assoc()) 
		{
			echo $row["status"];					// Echo data , equivalent with send data to esp
		}

?>