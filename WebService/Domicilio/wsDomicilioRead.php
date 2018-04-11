<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_POST["iddomicilio"])){
	$IdDomicilio =$_POST["iddomicilio"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM domicilio WHERE IdDomicilio = '$IdDomicilio'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Calle'] = utf8_encode($registro['Calle']);
		$registro['Colonia'] = utf8_encode($registro['Colonia']);
		$registro['Foto'] = utf8_encode($registro['Foto']);
		
		$json['Domicilio'][] = $registro;
	
	}else{
		$registro["IdDomicilio"] = 0;
		$registro["Numero"] = 0;
		$registro["Calle"] = '---';
		$registro["Colonia"] = '---';
		$registro["Foto"] = '---';
		$registro["FkUbicacion"] = 0;
		$json['Domicilio'][] = $registro;
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