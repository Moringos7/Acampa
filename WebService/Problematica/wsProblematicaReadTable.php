<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM problematica";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$registro['Sugerencia'] = utf8_encode($registro['Sugerencia']);
		
		$json['Problematica'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>