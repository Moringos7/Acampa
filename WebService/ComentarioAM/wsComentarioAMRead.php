<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idcomentarioam"])){
	$IdComentarioAM =$_GET["idcomentarioam"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM comentarioam WHERE IdComentarioAM = '$IdComentarioAM'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$json['AdultoMayor'][] = $registro;
		//echo $registro['IdAdultoMayor']."-".$registro['Nombre'];
	}else{
		$resultado["IdComentarioAM"] = 0;
		$resultado["Nombre"] = '---';
		$resultado["Fecha"] = '---';
		$resultado["Fk_AdultoMayor"] = 0;
	}
	mysqli_close($conexion);
	echo json_encode($json);
}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['ComentarioAM'][] = $resultado;
	echo json_encode($json);
}
?>