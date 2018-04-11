<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idasignacion"])){
	$IdAsignacion =$_POST["idasignacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM asignacion WHERE IdAsignacion = '$IdAsignacion'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$json['Asignacion'][] = $registro;
		//echo $registro['IdAsignacion']."-".$registro['Status'];
	}else{
		$registro["IdAsignacion"] = 0;
		$registro["Status"] = 0;
		$registro["Fecha"] = '---';
		$registro["FkUsuario"] = 0;
		$registro["FkAdultoMayor"] = 0;
		$json['Asignacion'][] = $registro;
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