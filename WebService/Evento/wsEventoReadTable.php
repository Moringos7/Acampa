<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM evento";	
	$resultado = mysqli_query($conexion,$select);
	
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$registro['Hora'] = utf8_encode($registro['Hora']);
		$registro['Lugar'] = utf8_encode($registro['Lugar']);
		$registro['Informacion'] = utf8_encode($registro['Informacion']);
		$json['Evento'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>