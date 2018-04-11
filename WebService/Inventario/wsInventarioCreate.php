<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$Producto = $_POST["producto"];
$Cantidad = $_POST["cantidad"];
$Existencia = $_POST["existencia"];
$Descripcion = $_POST["descripcion"];
$Imagen = $_POST["imagen"];
$Comentario = $_POST["comentario"];
$Extra = $_POST["extra"];


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