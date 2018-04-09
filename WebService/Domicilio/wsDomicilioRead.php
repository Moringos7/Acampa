<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["iddomicilio"])){
	$IdDomicilio =$_GET["iddomicilio"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM domicilio WHERE IdDomicilio = '$IdDomicilio'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Calle'] = utf8_encode($registro['Calle']);
		$registro['Colonia'] = utf8_encode($registro['Colonia']);
		$registro['Foto'] = utf8_encode($registro['Foto']);
		
		$json['Domicilio'][] = $registro;
	
	}else{
		$resultado["IdDomicilio"] = 0;
		$resultado["Numero"] = 0;
		$resultado["Calle"] = '---';
		$resultado["Colonia"] = '---';
		$resultado["Foto"] = '---';
		$resultado["FkUbicacion"] = 0;
		$json['Domicilio'][] = $resultado;
	}
	mysqli_close($conexion);
	echo json_encode($json);

}else{
	$resultado["success"] = 0;
	$resultado["message"] = "ws no Retorna";
	$json['Domicilio'][] = $resultado;
	echo json_encode($json);
}
?>