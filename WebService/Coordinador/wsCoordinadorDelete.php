<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idcoordinador"])){
	$IdCoordinador= $_POST["idcoordinador"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM coordinador  WHERE IdCoordinador = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdCoordinador);
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
