<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idusuario"])){
	$IdUsuario =$_GET["idusuario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM usuario WHERE IdUsuario = '$IdUsuario'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$registro['ApellidoPaterno'] = utf8_encode($registro['ApellidoPaterno']);
		$registro['ApellidoMaterno'] = utf8_encode($registro['ApellidoMaterno']);
		$registro['Correo'] = utf8_encode($registro['Correo']);
		$registro['Fotografia'] = utf8_encode($registro['Fotografia']);
		$json['Usuario'][] = $registro;
	}else{
		$resultado["IdUsuario"] = 0;
		$resultado["Nombre"] = '---';
		$resultado["ApellidoPaterno"] = '---';
		$resultado["ApellidoMaterno"] = '---';
		$resultado["Correo"] = '---';
		$resultado["Fotografia"] = '---';
		$resultado["FechaNacimiento"] = '---';
		$resultado["Fkseccion"] =0;
		$json['Usuario'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Usuario'][] = $resultado;
	echo json_encode($json);
}
?>