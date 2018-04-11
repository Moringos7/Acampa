<?php 
require("../wsBDcredencial.php");

if(isset($_POST["iddependencia"])){
	$IdDependencia = $_POST["iddependencia"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM dependencia WHERE IdDependencia = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdDependencia);
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
