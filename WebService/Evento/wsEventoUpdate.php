<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdEvento = $_POST["idevento"];
$Fecha = $_POST["fecha"];
$Hora = $_POST["hora"];
$Lugar = $_POST["lugar"];
$Informacion = $_POST["informacion"];
$FkTipoEvento = $_POST["fktipoevento"];


$sql = "UPDATE evento SET Fecha = ?, Hora = ?, Lugar = ?, Informacion = ?,FkTipoEvento = ? WHERE IdEvento = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssii',$Fecha,$Hora,$Lugar,$Informacion,$FkTipoEvento,$IdEvento);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>