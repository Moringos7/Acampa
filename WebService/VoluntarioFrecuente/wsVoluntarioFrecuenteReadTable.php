<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM voluntariofrecuente";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$json['VoluntarioFrecuente'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>