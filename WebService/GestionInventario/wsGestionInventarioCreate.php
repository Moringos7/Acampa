<?php
require("../wsBDcredencial.php");
$conexion = mysqli_connect($hostname,$username,$password,$database);
$Fecha = $_POST["fecha"];
$FkScouter = $_POST["fkscouter"];
$FkInventario = $_POST["fkinventario"];

$sql = "INSERT INTO gestioninventario VALUES(null,?,?,?)";

$stm = $conexion->prepare($sql);
$stm->bind_param('sii',$Fecha,$FkScouter,$FkInventario);
if($stm->execute()){
	echo "Creado";
}else{
	echo "No Creado";
}
mysqli_close($conexion);
?>