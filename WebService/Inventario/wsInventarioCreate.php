<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$Producto = $_GET["producto"];
$Cantidad = $_GET["cantidad"];
$Existencia = $_GET["existencia"];
$Descripcion = $_GET["descripcion"];
$Imagen = $_GET["imagen"];
$Comentario = $_GET["comentario"];
$Extra = $_GET["extra"];


$sql = "INSERT INTO inventario VALUES(null,?,?,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('sdisssi',$Producto,$Cantidad,$Existencia,$Descripcion,$Imagen,$Comentario,$Extra);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>