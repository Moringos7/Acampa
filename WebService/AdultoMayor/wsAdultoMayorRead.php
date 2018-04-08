<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idadultomayor"])){
	$IdAdultoMayor =$_GET["idadultomayor"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM adultomayor WHERE IdAdultoMayor = '$IdAdultoMayor'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$registro['ApellidoPaterno'] = utf8_encode($registro['ApellidoPaterno']);
		$registro['ApellidoMaterno'] = utf8_encode($registro['ApellidoMaterno']);
		$json['AdultoMayor'][] = $registro;
		//echo $registro['IdAdultoMayor']."-".$registro['Nombre'];
	}else{
		$resultado["IdAdultoMayor"] = 0;
		$resultado["Nombre"] = '---';
		$resultado["ApellidoPaterno"] = '---';
		$resultado["ApellidoMaterno"] = '---';
		$resultado["Fotografia"] = '---';
		$resultado["Diabetico"] = 0;
		$resultado["FkDependencia"] = 0;
		$resultado["FkDomicilio"] = 0;
		$json['AdultoMayor'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);
}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['AdultoMayor'][] = $resultado;
	echo json_encode($json);
}
?>