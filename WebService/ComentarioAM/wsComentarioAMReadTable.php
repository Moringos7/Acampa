<?php

require("../wsBDcredencial.php");
$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM comentarioam";	
	$resultado = mysqli_query($conexion,$select);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
	
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$json['ComentarioAM'][] = $registro;
	}
	//var_dump($json);
	mysqli_close($conexion);
	echo json_encode($json);
?>