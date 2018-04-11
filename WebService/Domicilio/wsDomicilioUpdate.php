<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdDomicilio = $_POST["iddomicilio"];
$Numero= $_POST["numero"];
$Calle = $_POST["calle"];
$Colonia = $_POST["colonia"];
$Foto = $_POST["foto"];
$FkUbicacion = $_POST["fkubicacion"];


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