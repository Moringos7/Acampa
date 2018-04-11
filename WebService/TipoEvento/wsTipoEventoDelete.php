<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idtipoevento"])){
	$IdTipoEvento = $_POST["idtipoevento"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM tipoevento WHERE IdTipoEvento = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdTipoEvento);
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
