<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idevento"])){
	$IdEvento = $_GET["idevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM evento WHERE IdEvento = '$IdEvento'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$registro['Hora'] = utf8_encode($registro['Hora']);
		$registro['Lugar'] = utf8_encode($registro['Lugar']);
		$registro['Comentario'] = utf8_encode($registro['Comentario']);
		
		$json['Evento'][] = $registro;
	
	}else{

		$resultado["IdEvento"] = 0;
		$resultado["Fecha"] = '---';
		$resultado["Hora"] = '---';
		$resultado["Lugar"] = '---';
		$resultado["Comentario"] = '---';
		$resultado["FkTipoEvento"] = 0;
		$json['Evento'][] = $resultado;
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