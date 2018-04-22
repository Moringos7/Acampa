<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Nombre = $_POST["nombre"];
$Fecha = $_POST["fecha"];
$FkAdultoMayor = $_POST["fkadultomayor"];

$sql = "INSERT INTO comentarioam VALUES(null,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssi',$Nombre,$Fecha,$FkAdultoMayor);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>