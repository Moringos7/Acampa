<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idgestioninventario"])){
	$IdGestionInventario = $_GET["idgestioninventario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM gestioninventario WHERE IdGestionInventario = '$IdGestionInventario'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Fecha'] = utf8_encode($registro['Fecha']);

		$json['GestionInventario'][] = $registro;
	
	}else{
		$resultado["IdGestionInventario"] = 0;
		$resultado["Fecha"] = '---';
		$resultado["FkScouter"] = 0;
		$resultado["FkInventario"] = 0;
		$json['GestionInventario'][] = $resultado;
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
