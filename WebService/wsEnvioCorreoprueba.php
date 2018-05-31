<?php 

	require("wsBDcredencial.php");
	$Packetjson = file_get_contents('php://input');
    $data = json_decode($Packetjson); 
    $Asunto = $data->asunto;
    $Correo = $data->correo;
    $Pass = $data->pass;
    $registro["Mensaje"] = "Asunto: ".$Asunto." correo: ".$Correo." Password: ".$Pass;
    $json['Correo'][] = $registro;
	echo json_encode($json);
?>