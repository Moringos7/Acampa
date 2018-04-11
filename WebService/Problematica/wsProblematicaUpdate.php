<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdProblematica = $_POST["idproblematica"];
$Fecha = $_POST["fecha"];
$Nombre = $_POST["nombre"];
$Sugerencia = $_POST["sugerencia"];
$FkUsuario = $_POST["fkusuario"];
$FkTipoProblematica = $_POST["fktipoproblematica"];


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