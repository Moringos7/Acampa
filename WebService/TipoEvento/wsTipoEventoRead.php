<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idtipoevento"])){
	$IdTipoEvento = $_POST["idtipoevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM tipoevento WHERE IdTipoEvento = '$IdTipoEvento'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		
		$json['TipoEvento'][] = $registro;
	
	}else{
		$registro["IdTipoEvento"] = 0;
		$registro["Nombre"] = '---';
		$json['TipoEvento'][] = $registro;
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