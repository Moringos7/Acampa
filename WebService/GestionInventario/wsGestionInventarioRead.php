<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["idgestioninventario"])){
	$IdGestionInventario = $_POST["idgestioninventario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM gestioninventario WHERE IdGestionInventario = '$IdGestionInventario'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);

		$json['GestionInventario'][] = $registro;
	
	}else{
		$registro["IdGestionInventario"] = 0;
		$registro["Fecha"] = '---';
		$registro["FkScouter"] = 0;
		$registro["FkInventario"] = 0;
		$json['GestionInventario'][] = $registro;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['GestionInventario'][] = $resultado;
	echo json_encode($json);
}
?>
