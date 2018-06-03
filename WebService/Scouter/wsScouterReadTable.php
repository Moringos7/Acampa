<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM scouter";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
			$registro['FechaInicio'] = utf8_encode($registro['FechaInicio']);
		$registro['FechaFinal'] = utf8_encode($registro['FechaFinal']);
		$json['Scouter'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>