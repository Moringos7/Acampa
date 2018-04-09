<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_GET["nombre"];

$sql = "INSERT INTO tipoevento  VALUES(null,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('s',$Nombre);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>