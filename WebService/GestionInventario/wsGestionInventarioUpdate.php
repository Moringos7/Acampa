<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdGestionInventario = $_GET["idgestioninventario"];
$Fecha = $_GET["fecha"];
$FkScouter = $_GET["fkscouter"];
$FkInventario = $_GET["fkinventario"];


$sql = "UPDATE gestioninventario SET Fecha = ?, FkScouter = ?, FkInventario = ? WHERE IdGestionInventario = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('siii',$Fecha,$FkScouter,$FkInventario,$IdGestionInventario);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>
