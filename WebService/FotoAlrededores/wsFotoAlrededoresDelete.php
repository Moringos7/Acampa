<?php 
require("../wsBDcredencial.php");

if(isset($_POST["idfotoalrededores"])){
	$IdFotoAlrededores = $_POST["idfotoalrededores"];
	$conexion = mysqli_connect($hostname,$username,$password,$database);
	$sql = "DELETE FROM fotoalrededores WHERE IdFotoAlrededores = ?";
	$stm = $conexion->prepare($sql);
	$stm->bind_param('i',$IdFotoAlrededores);
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