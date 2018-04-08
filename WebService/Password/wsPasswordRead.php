<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idpassword"])){
	$IdPassword = $_GET["idpassword"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM password WHERE IdPassword = '$IdPassword'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Password'] = utf8_encode($registro['Password']);
		
		$json['Password'][] = $registro;
	
	}else{
		$resultado["IdPassword"] = 0;
		$resultado["Password"] = '***';
		$resultado["Intentos"] = 0;
		$resultado["FkUsuario"] = 0;
		$json['Password'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Dependencia'][] = $resultado;
	echo json_encode($json);
}
?>