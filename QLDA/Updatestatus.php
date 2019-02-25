<?php

$server 	= "localhost:3306";	// Change this to correspond with your database port
$username 	= "root";			// Change if use webhost online
$password 	= "";
$DB 		= "qlda";			// database name

$Stt = $_POST["status"];		// Get data with variable is "status" sending from Android App 

			$update = new mysqli($server, $username, $password, $DB); // Check database connection
			if ($update->connect_error) {
				die("Connection failed: " . $update->connect_error);
			} 
			
			if($Stt == 1)								// If data == 1 
			{
				$sql = "UPDATE status SET status = 1";	// Update present status to database
			if ($update->query($sql) === TRUE) {		// If don't put this If , we can't change the value in database
				echo "1";								// Echo "1" , equivalent with send data to App to toast ON
			} 
			} 
				
			else if ($Stt == 0)							// If data == 0 
			{
				$sql = "UPDATE status SET status = 0";	// Update present status to database
			if ($update->query($sql) === TRUE) {		// If don't put this If , we can't change the value in database
				echo "0";								// Echo "0" , equivalent with send data to App to toast OFF
			} 
	
			} 
			
?>