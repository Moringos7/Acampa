<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdDomicilio = $_GET["iddomicilio"];
$Numero= $_GET["numero"];
$Calle = $_GET["calle"];
$Colonia = $_GET["colonia"];
$Foto = $_GET["foto"];
$FkUbicacion = $_GET["fkubicacion"];


$sql = "UPDATE domicilio SET Numero = ?, Calle = ?, Colonia = ?, Foto = ?,FkUbicacion = ? WHERE IdDomicilio = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('isssii',$Numero,$Calle,$Colonia,$Foto,$FkUbicacion,$IdDomicilio);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>