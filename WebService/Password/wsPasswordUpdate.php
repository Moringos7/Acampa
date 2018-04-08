<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdPassword = $_GET["idpassword"];
$Password = $_GET["password"];
$Intentos = $_GET["intentos"];
$FkUsuario = $_GET["fkusuario"];


$sql = "UPDATE password SET Password = ?, Intentos = ?, FkUsuario = ? WHERE IdPassword = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('siii',$Password,$Intentos,$FkUsuario,$IdPassword);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>