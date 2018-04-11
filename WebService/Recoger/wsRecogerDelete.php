<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idrecoger"])){
	$IdRecoger = $_POST["idrecoger"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM recoger WHERE IdRecoger = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdRecoger);
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
