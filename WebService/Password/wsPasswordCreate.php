<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Password = $_POST["password"];
$Intentos = $_POST["intentos"];
$FechaLogin = $_POST["fechalogin"];
$Status = $_POST["status"];
$FkUsuario = $_POST["fkusuario"];

$sql = "INSERT INTO password VALUES(null,?,?,?,?,?)";

$Password = hash('sha1',$Password, false); 

$stm = $conexion->prepare($sql);

$stm->bind_param('sisii',$Password,$Intentos,$FechaLogin,$Status,$FkUsuario);

if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>