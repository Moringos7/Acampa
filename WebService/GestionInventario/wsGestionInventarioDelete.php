<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idgestioninventario"])){
	$IdGestionInventario = $_GET["idgestioninventario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM gestioninventario WHERE IdGestionInventario = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdGestionInventario);
	if($stm->execute()){
		echo "Eliminado";
	}else{
		echo "NoEliminado";
	}
	mysqli_close($conexion);
}else{
	echo "NoExiste";
}
?>

