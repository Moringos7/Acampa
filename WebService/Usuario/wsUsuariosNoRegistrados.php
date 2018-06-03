<?php
require("../wsBDcredencial.php");
$json = array();
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT usuario.* FROM usuario,password WHERE IdUsuario = FkUsuario AND Status= 0 AND FechaLogin = '0000-00-00'";	
	$resultado = mysqli_query($conexion,$select);
	//var_dump($resultado);
	 $valorCheck = true;
		while($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
			$valorCheck = false;
			$registro['Nombre'] = utf8_encode($registro['Nombre']);
			$registro['ApellidoPaterno'] = utf8_encode($registro['ApellidoPaterno']);
			$registro['ApellidoMaterno'] = utf8_encode($registro['ApellidoMaterno']);
			$registro['Correo'] = utf8_encode($registro['Correo']);
			$registro['Fotografia'] = utf8_encode($registro['Fotografia']);
			$json['Usuario'][] = $registro;
		}
		$registro['Check'] = $valorCheck;
		$json['Usuario'][] = $registro;
	mysqli_close($conexion);
	echo json_encode($json);
?>