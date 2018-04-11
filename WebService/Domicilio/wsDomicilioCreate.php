<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Numero= $_POST["numero"];
$Calle = $_POST["calle"];
$Colonia = $_POST["colonia"];
$Foto = $_POST["foto"];
$FkUbicacion = $_POST["fkubicacion"];

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