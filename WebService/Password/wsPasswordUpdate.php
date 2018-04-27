<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdPassword = $_POST["idpassword"];
$Password = $_POST["password"];
$Intentos = $_POST["intentos"];
$FechaLogin = $_POST["fechalogin"];
$FkUsuario = $_POST["fkusuario"];


$sql = "UPDATE password SET Password = ?, Intentos = ?,FechaLogin = ?, FkUsuario = ? WHERE IdPassword = ?";

$Password = hash('sha1',$Password, false); 

$stm = $conexion->prepare($sql);
$stm->bind_param('sisii',$Password,$Intentos,$FechaLogin,$FkUsuario,$IdPassword);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>