<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idcoordinador"])){
	$IdCoordinador =$_GET["idcoordinador"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM coordinador WHERE IdCoordinador = '$IdCoordinador'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado)){

		$json['Coordinador'][] = $registro;
	
	}else{
		$resultado["IdCoordinador"] = 0;
		$resultado["FkScouter"] = 0;
		$json['Coordinador'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Coordinador'][] = $resultado;
	echo json_encode($json);
}
?>