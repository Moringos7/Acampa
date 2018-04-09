<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idtipoproblematica"])){
	$IdTipoProblematica = $_GET["idtipoproblematica"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM tipoproblematica WHERE IdTipoProblematica = '$IdTipoProblematica'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		
		$json['TipoProblematica'][] = $registro;
	
	}else{
		$resultado["IdTipoProblematica"] = 0;
		$resultado["Nombre"] = '---';
		$json['TipoProblematica'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['TipoProblematica'][] = $resultado;
	echo json_encode($json);
}
?>