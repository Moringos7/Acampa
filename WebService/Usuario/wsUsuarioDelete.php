<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idusuario"])){
	$IdUsuario = $_POST["idusuario"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM usuario WHERE IdUsuario = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdUsuario);
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
