<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idseccion"])){
	$IdSeccion =$_POST["idseccion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM seccion WHERE IdSeccion = '$IdSeccion'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		
		$json['Seccion'][] = $registro;
	
	}else{
		$registro["IdSeccion"] = 0;
		$registro["Nombre"] = '---';
		$json['Seccion'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Seccion'][] = $resultado;
	echo json_encode($json);
}
?>