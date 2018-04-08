<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Status= $_GET["status"];
$Fecha = $_GET["fecha"];
$FkUsuario = $_GET["fkusuario"];
$FkAdultoMayor = $_GET["fkadultomayor"];

$sql = "INSERT INTO asignacion VALUES(null,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('isii',$Status,$Fecha,$FkUsuario,$FkAdultoMayor);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>