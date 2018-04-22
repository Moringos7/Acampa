<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idcomentarioam"])){
	$IdComentarioAM = $_POST["idcomentarioam"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM comentarioam WHERE IdComentarioAM = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdComentarioAM);
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