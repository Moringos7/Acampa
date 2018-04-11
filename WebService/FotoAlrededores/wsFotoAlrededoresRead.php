<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idfotoalrededores"])){
	$IdFotoAlrededores = $_POST["idfotoalrededores"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM fotoalrededores WHERE IdFotoAlrededores = '$IdFotoAlrededores'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		$registro['Foto'] = utf8_encode($registro['Foto']);
		$json['FotoAlrededores'][] = $registro;
	
	}else{
		$registro["IdFotoAlrededores"] = 0;
		$registro["Foto"] = '---';
		$registro["FkDomicilio"] = 0;
		$json['FotoAlrededores'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['FotoAlrededores'][] = $resultado;
	echo json_encode($json);
}
?>