<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM fotoalrededores";	
	$resultado = mysqli_query($conexion,$select);

	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Foto'] = utf8_encode($registro['Foto']);
		$json['FotoAlrededores'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>