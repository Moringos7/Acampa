<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idscouter"])){
	$IdScouter = $_POST["idscouter"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM scouter WHERE IdScouter = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdScouter);
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
