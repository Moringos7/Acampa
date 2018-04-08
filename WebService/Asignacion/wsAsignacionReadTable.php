<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM asignacion";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado)){
		$json['Asignacion'][] = $registro;
		//echo $registro['IdAsignacion']."-".$registro['Status']."---";
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>