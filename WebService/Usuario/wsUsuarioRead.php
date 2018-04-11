<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idusuario"])){
	$IdUsuario =$_POST["idusuario"];
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
		$registro["IdUsuario"] = 0;
		$registro["Nombre"] = '---';
		$registro["ApellidoPaterno"] = '---';
		$registro["ApellidoMaterno"] = '---';
		$registro["Correo"] = '---';
		$registro["Fotografia"] = '---';
		$registro["FechaNacimiento"] = '---';
		$registro["Fkseccion"] =0;
		$json['Usuario'][] = $registro;
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