<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idubicacion"])){
	$IdDomicilio =$_POST["idubicacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM ubicacion WHERE IdUbicacion = '$IdUbicacion'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$json['Ubicacion'][] = $registro;
	
	}else{
		$registro["IdUbicacion"] = 0;
		$registro["Longitud"] = 0;
		$registro["Latitud"] = 0;

		$json['Ubicacion'][] = $registro;
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