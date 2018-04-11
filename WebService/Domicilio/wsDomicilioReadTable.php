<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM domicilio";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Calle'] = utf8_encode($registro['Calle']);
		$registro['Colonia'] = utf8_encode($registro['Colonia']);
		$registro['Foto'] = utf8_encode($registro['Foto']);
		$json['Domicilio'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>