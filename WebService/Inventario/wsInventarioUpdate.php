<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdInventario = $_POST["idinventario"];
$Producto = $_POST["producto"];
$Cantidad = $_POST["cantidad"];
$Existencia = $_POST["existencia"];
$Descripcion = $_POST["descripcion"];
$Imagen = $_POST["imagen"];
$Comentario = $_POST["comentario"];
$Extra = $_POST["extra"];


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