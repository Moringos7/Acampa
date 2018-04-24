<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$FechaInicio = $_POST["fechainicio"];
$FechaFinal = $_POST["fechafinal"];
$FkUsuario = $_POST["fkusuario"];

$sql = "INSERT INTO scouter VALUES(null,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssi',$FechaInicio,$FechaFinal,$FkUsuario);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>