<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdInventario = $_GET["idinventario"];
$Producto = $_GET["producto"];
$Cantidad = $_GET["cantidad"];
$Existencia = $_GET["existencia"];
$Descripcion = $_GET["descripcion"];
$Imagen = $_GET["imagen"];
$Comentario = $_GET["comentario"];
$Extra = $_GET["extra"];


$sql = "UPDATE inventario SET Producto = ?, Cantidad = ?, Existencia = ?, Descripcion = ?,Imagen = ?, Comentario = ?, Extra = ? WHERE IdInventario = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('isssii',$Producto,$Cantidad,$Existencia,$Descripcion,$Imagen,$Comentario,$Extra,$IdInventario);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>