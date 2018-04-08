<?php

require("../wsBDcredencial.php");
$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM adultomayor";	
	$resultado = mysqli_query($conexion,$select);
	//echo "Hola";
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
	
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$registro['ApellidoPaterno'] = utf8_encode($registro['ApellidoPaterno']);
		$registro['ApellidoMaterno'] = utf8_encode($registro['ApellidoMaterno']);
		$json['AdultoMayor'][] = $registro;
	}
	
	//var_dump($json);
	mysqli_close($conexion);
	echo json_encode($json);
?>