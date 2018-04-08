<?php 
require("../wsBDcredencial.php");

if(isset($_GET["idadultomayor"])){
	$IdAdultoMayor = $_GET["idadultomayor"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM adultomayor WHERE IdAdultoMayor = ? ";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdAdultoMayor);
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
