<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idtipoproblematica"])){
	$IdTipoProblematica = $_POST["idtipoproblematica"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM tipoproblematica WHERE IdTipoProblematica = '$IdTipoProblematica'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Nombre'] = utf8_encode($registro['Nombre']);
		
		$json['TipoProblematica'][] = $registro;
	
	}else{
		$registro["IdTipoProblematica"] = 0;
		$registro["Nombre"] = '---';
		$json['TipoProblematica'][] = $registro;
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