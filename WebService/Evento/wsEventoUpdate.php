<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdEvento = $_GET["idevento"];
$Fecha = $_GET["fecha"];
$Hora = $_GET["hora"];
$Lugar = $_GET["lugar"];
$Comentario = $_GET["comentario"];
$FkTipoEvento = $_GET["fktipoevento"];


$sql = "UPDATE evento SET Fecha = ?, Hora = ?, Lugar = ?, Comentario = ?,FkTipoEvento = ? WHERE IdEvento = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssii',$Fecha,$Hora,$Lugar,$Comentario,$FkTipoEvento,$IdEvento);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>