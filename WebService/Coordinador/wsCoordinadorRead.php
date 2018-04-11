<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idcoordinador"])){
	$IdCoordinador =$_POST["idcoordinador"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM coordinador WHERE IdCoordinador = '$IdCoordinador'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){

		$json['Coordinador'][] = $registro;
	
	}else{
		$registro["IdCoordinador"] = 0;
		$registro["FkScouter"] = 0;
		$json['Coordinador'][] = $registro;
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