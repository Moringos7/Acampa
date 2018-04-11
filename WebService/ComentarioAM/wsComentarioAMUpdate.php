<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdComentarioAM = $_POST["idcomentarioam"];
$Nombre = $_POST["nombre"];
$Fecha = $_POST["fecha"];
$FkAdultoMayor = $_POST["fkadultomayor"];


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