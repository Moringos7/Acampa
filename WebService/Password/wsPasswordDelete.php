<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idpassword"])){
	$IdPassword = $_POST["idpassword"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM password WHERE IdPassword = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdPassword);
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
