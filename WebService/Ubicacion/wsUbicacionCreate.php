<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Longitud = $_POST["longitud"];
$Latitud = $_POST["latitud"];

$sql = "INSERT INTO ubicacion VALUES(null,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('dd',$Longitud,$Latitud);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>