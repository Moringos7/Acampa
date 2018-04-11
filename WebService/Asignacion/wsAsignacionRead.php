<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idasignacion"])){
	$IdAsignacion =$_GET["idasignacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM asignacion WHERE IdAsignacion = '$IdAsignacion'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$json['Asignacion'][] = $registro;
		//echo $registro['IdAsignacion']."-".$registro['Status'];
	}else{
		$resultado["IdAsignacion"] = 0;
		$resultado["Status"] = 0;
		$resultado["Fecha"] = '---';
		$resultado["FkUsuario"] = 0;
		$resultado["FkAdultoMayor"] = 0;
		$json['Asignacion'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Asignacion'][] = $resultado;
	echo json_encode($json);
}
?>