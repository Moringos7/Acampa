<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Numero= $_GET["numero"];
$Calle = $_GET["calle"];
$Colonia = $_GET["colonia"];
$Foto = $_GET["foto"];
$FkUbicacion = $_GET["fkubicacion"];

$sql = "INSERT INTO domicilio VALUES(null,?,?,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('isssi',$Numero,$Calle,$Colonia,$Foto,$FkUbicacion);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>