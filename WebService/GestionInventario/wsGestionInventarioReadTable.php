<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM gestioninventario";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$json['GestionInventario'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>


