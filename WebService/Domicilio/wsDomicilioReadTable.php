<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM domicilio";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$resultado['Calle'] = utf8_encode($resultado['Calle']);
		$resultado['Colonia'] = utf8_encode($resultado['Colonia']);
		$resultado['Foto'] = utf8_encode($resultado['Foto']);
		$json['Domicilio'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>