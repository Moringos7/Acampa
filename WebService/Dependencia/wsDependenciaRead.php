<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["iddependencia"])){
	$IdDependencia =$_GET["iddependencia"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM dependencia WHERE IdDependencia = '$IdDependencia'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		$json['Dependencia'][] = $registro;
		//echo $registro['IdAsignacion']."-".$registro['Status'];
	}else{
		$resultado["IdDependencia"] = 0;
		$resultado["Nombre"] = '---';
		$json['Dependencia'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Dependencia'][] = $resultado;
	echo json_encode($json);
}
?>