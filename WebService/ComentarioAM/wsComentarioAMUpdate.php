<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdComentarioAM = $_GET["idcomentarioam"];
$Nombre = $_GET["nombre"];
$Fecha = $_GET["fecha"];
$FkAdultoMayor = $_GET["fkadultomayor"];


$sql = "UPDATE comentarioam SET Nombre = ?, Fecha = ?,FkAdultoMayor = ? WHERE IdComentarioAM= ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssii',$Nombre,$Fecha,$FkAdultoMayor,$IdComentarioAM);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>