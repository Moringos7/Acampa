<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idvoluntariofrecuente"])){
	$IdDomicilio =$_POST["idvoluntariofrecuente"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM voluntariofrecuente WHERE IdVoluntarioFrecuente = '$IdVoluntarioFrecuente'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){

		$json['VoluntarioFrecuente'][] = $registro;
	
	}else{
		$registro["IdVoluntarioFrecuente"] = 0;
		$registro["FkUsuario"] = 0;
		$registro["FkAdultoMayor"] = 0;
		$json['VoluntarioFrecuente'][] = $registro;
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