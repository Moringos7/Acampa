<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Fecha = $_POST["fecha"];
$Nombre = $_POST["nombre"];
$Sugerencia = $_POST["sugerencia"];
$FkUsuario = $_POST["fkusuario"];
$FkTipoProblematica = $_POST["fktipoproblematica"];

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