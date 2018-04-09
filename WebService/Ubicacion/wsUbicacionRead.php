<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idubicacion"])){
	$IdDomicilio =$_GET["idubicacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM ubicacion WHERE IdUbicacion = '$IdUbicacion'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$json['Ubicacion'][] = $registro;
	
	}else{
		$resultado["IdUbicacion"] = 0;
		$resultado["Longitud"] = 0;
		$resultado["Latitud"] = 0;

		$json['Ubicacion'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Ubicacion'][] = $resultado;
	echo json_encode($json);
}
?>