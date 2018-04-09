<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idseccion"])){
	$IdSeccion = $_GET["idseccion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM seccion WHERE IdSeccion = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdSeccion);
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
