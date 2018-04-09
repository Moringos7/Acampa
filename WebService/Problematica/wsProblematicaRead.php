<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idproblematica"])){
	$IdProblematica = $_GET["idproblematica"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM problematica WHERE IdProblematica = '$IdProblematica'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$registro['Sugerencia'] = utf8_encode($registro['Sugerencia']);
		
		$json['Problematica'][] = $registro;
	
	}else{
		$resultado["IdProblematica"] = 0;
		$resultado["Fecha"] = '---';
		$resultado["Nombre"] = '---';
		$resultado["Sugerencia"] = '---';
		$resultado["FkUsuario"] = 0;
		$resultado["FkTipoProblematica"] = 0;
		$json['Problematica'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Problematica'][] = $resultado;
	echo json_encode($json);
}
?>