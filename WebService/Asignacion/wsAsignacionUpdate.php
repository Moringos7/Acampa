<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdAsignacion = $_POST["idasignacion"];
$Status= $_POST["status"];
$Fecha = $_POST["fecha"];
$FkUsuario = $_POST["fkusuario"];
$FkAdultoMayor = $_POST["fkadultomayor"];


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