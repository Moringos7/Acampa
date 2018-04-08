<?php 
require("../wsBDcredencial.php");

$conexion =mysqli_connect($hostname,$username,$password,$database);

$IdCoordinador = $_GET["idcoordinador"];
$FkScouter = $_GET["fkscouter"];

$sql = "UPDATE coordinador SET FkScouter = ? WHERE IdCoordinador = ?";

$stm = $conexion->prepare($sql);
$stm->bind_param('ii'$FkScouter,$IdCoordinador);
if($stm->execute()){
	echo "Actualizado";
}else{
	echo "NoActulizado";
}
mysqli_close($conexion);
?>