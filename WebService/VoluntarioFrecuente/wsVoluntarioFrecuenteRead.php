<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idvoluntariofrecuente"])){
	$IdDomicilio =$_GET["idvoluntariofrecuente"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM voluntariofrecuente WHERE IdVoluntarioFrecuente = '$IdVoluntarioFrecuente'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){

		$json['VoluntarioFrecuente'][] = $registro;
	
	}else{
		$resultado["IdVoluntarioFrecuente"] = 0;
		$resultado["FkUsuario"] = 0;
		$resultado["FkAdultoMayor"] = 0;
		$json['VoluntarioFrecuente'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['VoluntarioFrecuente'][] = $resultado;
	echo json_encode($json);
}
?>