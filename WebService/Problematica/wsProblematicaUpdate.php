<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdProblematica = $_GET["idproblematica"];
$Fecha = $_GET["fecha"];
$Nombre = $_GET["nombre"];
$Sugerencia = $_GET["sugerencia"];
$FkUsuario = $_GET["fkusuario"];
$FkTipoProblematica = $_GET["fktipoproblematica"];


$sql = "UPDATE problematica SET Fecha = ?, Nombre = ?, Sugerencia = ?, FkUsuario = ?,FkTipoProblematica = ? WHERE IdProblematica = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('sssiii',$Fecha,$Nombre,$Sugerencia,$FkUsuario,$FkTipoProblematica,$IdProblematica);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>