<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Fecha = $_GET["fecha"];
$Nombre = $_GET["nombre"];
$Sugerencia = $_GET["sugerencia"];
$FkUsuario = $_GET["fkusuario"];
$FkTipoProblematica = $_GET["fktipoproblematica"];

$sql = "INSERT INTO problematica VALUES(null,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('sssii',$Fecha,$Nombre,$Sugerencia,$FkUsuario,$FkTipoProblematica);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>