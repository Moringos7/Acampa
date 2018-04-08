<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);

$Fecha = $_GET["fecha"];
$Hora = $_GET["hora"];
$Lugar = $_GET["lugar"];
$Comentario = $_GET["comentario"];
$FkTipoEvento = $_GET["fktipoevento"];


$sql = "INSERT INTO evento VALUES(null,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('ssssi',$Fecha,$Hora,$Lugar,$Comentario,$FkTipoEvento);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>