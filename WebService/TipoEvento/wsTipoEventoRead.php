<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idtipoevento"])){
	$IdTipoEvento = $_GET["idtipoevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM tipoevento WHERE IdTipoEvento = '$IdTipoEvento'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		
		$json['TipoEvento'][] = $registro;
	
	}else{
		$resultado["IdTipoEvento"] = 0;
		$resultado["Nombre"] = '---';
		$json['TipoEvento'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['TipoEvento'][] = $resultado;
	echo json_encode($json);
}
?>