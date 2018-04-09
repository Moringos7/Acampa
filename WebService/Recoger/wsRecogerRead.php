<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idrecoger"])){
	$IdRecoger =$_GET["idrecoger"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM recoger WHERE IdRecoger = '$IdRecoger'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$json['Recoger'][] = $registro;
	
	}else{
		$resultado["IdRecoger"] = '---';
		$resultado["FkScouter"] = '---';
		$resultado["FkAsignacion"] = '---';
		$json['Recoger'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Recoger'][] = $resultado;
	echo json_encode($json);
}
?>