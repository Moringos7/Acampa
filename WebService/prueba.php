<?php
	require("wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$nombre = $_POST['NOMBRE'];
	$dato = $_POST['DATO'];
	$query="INSERT INTO prueba VALUES (NULL,'$nombre','$dato')";
	if(mysqli_query($conexion,$query)){
		echo "Insertado";
	}else{
		echo "Fallo";
	}
?>