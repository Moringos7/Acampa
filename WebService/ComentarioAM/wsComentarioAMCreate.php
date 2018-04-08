<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_GET["nombre"];
$Fecha = $_GET["fecha"];
$FkAdultoMayor = $_GET["fkadultomayor"];

$sql = "INSERT INTO comentarioam VALUES(null,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssi',$Nombre,$fecha,$FkComentarioAM);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>