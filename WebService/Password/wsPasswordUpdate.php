<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdPassword = $_POST["idpassword"];
$Password = $_POST["password"];
$Intentos = $_POST["intentos"];
$FkUsuario = $_POST["fkusuario"];


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