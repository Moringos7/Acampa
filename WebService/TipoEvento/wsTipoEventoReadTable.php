<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM tipoevento";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$resultado['Nombre'] = utf8_encode($resultado['Nombre']);
		$json['TipoEvento'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>