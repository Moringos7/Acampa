<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idubicacion"])){
	$IdUbicacion = $_GET["idubicacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM ubicacion WHERE IdUbicacion = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdUbicacion);
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
