<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdScouter = $_POST["idscouter"];
$FechaInicio = $_POST["fechainicio"];
$FechaFinal = $_POST["fechafinal"];
$FkUsuario = $_POST["fkusuario"];

$sql = "UPDATE scouter SET FechaInicio = ?, FechaFinal = ?, FkUsuario = ? WHERE IdScouter = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssii',$FechaInicio,$FechaFinal,$FkUsuario,$IdScouter);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>