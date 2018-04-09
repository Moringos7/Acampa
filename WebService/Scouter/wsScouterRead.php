<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idscouter"])){
	$IdScouter = $_GET["idscouter"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM scouter WHERE IdScouter = '$IdScouter'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['FechaInicio'] = utf8_encode($registro['FechaInicio']);
		$registro['FechaFinal'] = utf8_encode($registro['FechaFinal']);
		$json['Scouter'][] = $registro;
	
	}else{
		$resultado["IdScouter"] = 0;
		$resultado["FechaInicio"] = '---';
		$resultado["FechaFinal"] = '---';
		$resultado["FkUsuario"] = 0;
		$json['Scouter'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Scouter'][] = $resultado;
	echo json_encode($json);
}
?>