<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idasignacion"])){
	$IdAsignacion = $_POST["idasignacion"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM asignacion WHERE IdAsignacion = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdAsignacion);
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
