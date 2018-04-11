<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idtipoproblematica"])){
	$IdTipoProblematica = $_POST["idtipoproblematica"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM tipoproblematica WHERE IdTipoProblematica = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdTipoProblematica);
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
