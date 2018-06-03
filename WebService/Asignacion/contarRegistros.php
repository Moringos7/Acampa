<?php

	require("../wsBDcredencial.php");
	$conexion = mysqli_connect($hostname,$username,$password,$database);
		
	
	$Packetjson = file_get_contents('php://input');
	$data = json_decode($Packetjson);
	$Fecha = $data->fecha;
	if($Fecha != ""){
		$select = "SELECT Count(FkUsuario) as 'Cuenta' FROM asignacion WHERE Fecha = '$Fecha' AND FkAdultoMayor IS null AND Status = 0";
		$resultado = mysqli_query($conexion,$select);
		if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
			$json['Respuesta'][] = $registro;
		}else{
			$cuenta['Cuenta'] = 0;
			$json['Respuesta'][] = $cuenta;
		}
	}else{

		$cuenta['Cuenta'] = 0;
		$json['Respuesta'][] = $cuenta;
	}
	mysqli_close($conexion);
	echo json_encode($json);
?>