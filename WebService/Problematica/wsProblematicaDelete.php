<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idproblematica"])){
	$IdProblematica = $_POST["idproblematica"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM problematica WHERE IdProblematica = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdProblematica);
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
