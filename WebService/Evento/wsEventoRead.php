<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idevento"])){
	$IdEvento = $_POST["idevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM evento WHERE IdEvento = '$IdEvento'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$registro['Hora'] = utf8_encode($registro['Hora']);
		$registro['Lugar'] = utf8_encode($registro['Lugar']);
		$registro['Informacion'] = utf8_encode($registro['Informacion']);
		
		$json['Evento'][] = $registro;
	
	}else{

		$registro["IdEvento"] = 0;
		$registro["Fecha"] = '---';
		$registro["Hora"] = '---';
		$registro["Lugar"] = '---';
		$registro["Informacion"] = '---';
		$registro["FkTipoEvento"] = 0;
		$json['Evento'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Evento'][] = $resultado;
	echo json_encode($json);
}
?>