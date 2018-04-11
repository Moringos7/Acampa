<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idgestioninventario"])){
	$IdGestionInventario = $_POST["idgestioninventario"];
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

