<?php

require("../wsBDcredencial.php");

$json = array();
if(isset($_GET["idinventario"])){
	$IdInventario = $_GET["idinventario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$select = "SELECT * FROM inventario WHERE IdInventario = '$IdInventario'";	
	$resultado = mysqli_query($conexion,$select);
	
	if($registro = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
		
		$registro['Producto'] = utf8_encode($registro['Producto']);
		$registro['Descripcion'] = utf8_encode($registro['Descripcion']);
		$registro['Imagen'] = utf8_encode($registro['Imagen']);
		$registro['Comentario'] = utf8_encode($registro['Comentario']);
		
		$json['Inventario'][] = $registro;
	
	}else{
		$resultado["IdInventario"] = 0;
		$resultado["Producto"] = '---';
		$resultado["Cantidad"] = 0;
		$resultado["Existencia"] = 0;
		$resultado["Descripcion"] = '---';
		$resultado["Imagen"] = '---';
		$resultado["Comentario"] = '---';
		$resultado["Extra"] = 0;
		$json['Inventario'][] = $resultado;
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

$Producto = $_GET["producto"];
$Cantidad = $_GET["cantidad"];
$Existencia = $_GET["existencia"];
$Descripcion = $_GET["descripcion"];
$Imagen = $_GET["imagen"];
$Comentario = $_GET["comentario"];
$Extra = $_GET["extra"];
