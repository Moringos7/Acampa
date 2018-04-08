<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdAsignacion = $_GET["idasignacion"];
$Status= $_GET["status"];
$Fecha = $_GET["fecha"];
$FkUsuario = $_GET["fkusuario"];
$FkAdultoMayor = $_GET["fkadultomayor"];


$sql = "UPDATE asignacion SET Status = ?, Fecha = ?, FkUsuario = ?,FkAdultoMayor = ? WHERE IdAsignacion = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('isiii',$Status,$Fecha,$FkUsuario,$FkAdultoMayor,$IdAsignacion);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>