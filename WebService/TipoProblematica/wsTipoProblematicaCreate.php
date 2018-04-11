<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_POST["nombre"];

$sql = "INSERT INTO tipoproblematica  VALUES(null,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('s',$Nombre);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>