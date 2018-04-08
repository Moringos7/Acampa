<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idevento"])){
	$IdEvento = $_GET["idevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM evento WHERE IdEvento = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdEvento);
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
