<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idvoluntariofrecuente"])){
	$IdDomicilio = $_GET["idvoluntariofrecuente"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM voluntariofrecuente WHERE IdVoluntarioFrecuente = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdVoluntarioFrecuente);
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
