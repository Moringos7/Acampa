<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Password = $_POST["password"];
$Intentos = $_POST["intentos"];
$FkUsuario = $_POST["fkusuario"];

$sql = "INSERT INTO password VALUES(null,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('sii',$Password,$Intentos,$FkUsuario);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>