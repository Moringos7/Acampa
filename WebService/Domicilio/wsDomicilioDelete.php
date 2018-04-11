<?php 
require("../wsBDcredencial.php");

if(isset($_POST["iddomicilio"])){
	$IdDomicilio = $_POST["iddomicilio"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM domicilio WHERE IdDomicilio = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdDomicilio);
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
