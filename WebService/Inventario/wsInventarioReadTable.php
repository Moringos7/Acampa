<?php

require("../wsBDcredencial.php");

$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM inventario";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Producto'] = utf8_encode($registro['Producto']);
		$registro['Descripcion'] = utf8_encode($registro['Descripcion']);
		$registro['Imagen'] = utf8_encode($registro['Imagen']);
		$registro['Comentario'] = utf8_encode($registro['Comentario']);
		$json['Inventario'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>