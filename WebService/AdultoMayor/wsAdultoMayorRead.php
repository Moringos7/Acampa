<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idadultomayor"])){
	$IdAdultoMayor =$_POST["idadultomayor"];
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
		$registro["IdAdultoMayor"] = 0;
		$registro["Nombre"] = '---';
		$registro["ApellidoPaterno"] = '---';
		$registro["ApellidoMaterno"] = '---';
		$registro["Fotografia"] = '---';
		$registro["Diabetico"] = 0;
		$registro["FkDependencia"] = 0;
		$registro["FkDomicilio"] = 0;
		$json['AdultoMayor'][] = $registro;
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