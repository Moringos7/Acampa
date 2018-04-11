<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idpassword"])){
	$IdPassword = $_POST["idpassword"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM password WHERE IdPassword = '$IdPassword'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Password'] = utf8_encode($registro['Password']);
		
		$json['Password'][] = $registro;
	
	}else{
		$registro["IdPassword"] = 0;
		$registro["Password"] = '***';
		$registro["Intentos"] = 0;
		$registro["FkUsuario"] = 0;
		$json['Password'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Password'][] = $resultado;
	echo json_encode($json);
}
?>