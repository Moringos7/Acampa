<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdSeccion = $_POST["idseccion"];
$Nombre = $_POST["nombre"];


$sql = "UPDATE seccion SET Nombre = ? WHERE IdSeccion = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('si',$Nombre,$IdSeccion);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>