<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdTipoProblematica = $_POST["idtipoproblematica"];
$Nombre = $_POST["nombre"];


$sql = "UPDATE tipoproblematica SET Nombre = ? WHERE IdTipoProblematica = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('si',$Nombre,$IdTipoProblematica);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>