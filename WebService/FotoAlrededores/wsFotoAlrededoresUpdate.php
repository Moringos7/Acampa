<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdFotoAlrededores = $_GET["idfotoalrededores"];
$Foto = $_GET["foto"];
$FkDomicilio = $_GET["fkdomicilio"];


$sql = "UPDATE fotoalrededores SET Foto = ?,FkDomicilio = ? WHERE IdFotoAlrededores = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('sii',$Foto,$FkDomicilio,$IdFotoAlrededores);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>