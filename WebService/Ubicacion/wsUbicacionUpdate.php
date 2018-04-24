<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdUbicacion = $_POST["idubicacion"];
$Longitud = $_POST["longitud"];
$Latitud = $_POST["latitud"];


$sql = "UPDATE ubicacion SET Longitud = ?, Latitud = ? WHERE IdUbicacion = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ddi',$Longitud,$Latitud,$IdUbicacion);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>